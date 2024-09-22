package com.team.bottles.feat.setting.account

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCase
import com.team.bottles.core.domain.auth.usecase.LogOutUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.user.usecase.UpdateActivateMatchingUseCase
import com.team.bottles.feat.setting.account.mvi.AccountSettingIntent
import com.team.bottles.feat.setting.account.mvi.AccountSettingSideEffect
import com.team.bottles.feat.setting.account.mvi.AccountSettingUiState
import com.team.bottles.feat.setting.account.mvi.SettingAlertDialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountSettingViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateActivateMatchingUseCase: UpdateActivateMatchingUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<AccountSettingUiState, AccountSettingSideEffect, AccountSettingIntent>(
    savedStateHandle
) {

    init {
        launch {
            val profile = getUserProfileUseCase()
            reduce { copy(isMatchActivated = profile.isMatchActivated) }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): AccountSettingUiState =
        AccountSettingUiState()

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: AccountSettingIntent) {
        when (intent) {
            is AccountSettingIntent.ClickConfirmDialogButton -> confirm()
            is AccountSettingIntent.ClickDismissDialogButton -> dismiss()
            is AccountSettingIntent.ClickLogOutButton -> showLogoutDialog()
            is AccountSettingIntent.ClickDeleteUserButton -> showDeleteUserDialog()
            is AccountSettingIntent.ClickBackButton -> navigateToMyPage()
            is AccountSettingIntent.ClickMatchingActiveToggleButton -> changeMatchingActive()
        }
    }

    private fun confirm() {
        launch {
            when (currentState.dialogType) {
                SettingAlertDialogType.LOG_OUT -> logOutUseCase()
                SettingAlertDialogType.DELETE_USER -> deleteUserUseCase()
            }
            reduce { copy(showDialog = false) }
            postSideEffect(AccountSettingSideEffect.NavigateToLoginEndpoint)
        }
    }

    private fun dismiss() {
        reduce { copy(showDialog = false) }
    }

    private fun showLogoutDialog() {
        reduce {
            copy(
                showDialog = true,
                dialogType = SettingAlertDialogType.LOG_OUT
            )
        }
    }

    private fun showDeleteUserDialog() {
        reduce {
            copy(
                showDialog = true,
                dialogType = SettingAlertDialogType.DELETE_USER
            )
        }
    }

    private fun navigateToMyPage() {
        postSideEffect(AccountSettingSideEffect.NavigateToMyPage)
    }

    private fun changeMatchingActive() {
        launch {
            updateActivateMatchingUseCase(isActivate = !currentState.isMatchActivated)
            reduce { copy(isMatchActivated = !isMatchActivated) }
        }
    }

}
