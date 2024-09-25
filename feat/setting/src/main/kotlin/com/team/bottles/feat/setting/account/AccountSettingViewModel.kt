package com.team.bottles.feat.setting.account

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCase
import com.team.bottles.core.domain.auth.usecase.LogOutUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.user.usecase.UpdateActivateMatchingUseCase
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
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
        initAccountSetting()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): AccountSettingUiState =
        AccountSettingUiState()

    override fun handleClientException(throwable: Throwable) {
        if (currentState.accountSettingState == AccountSettingUiState.AccountSettingState.INIT) {
            showErrorScreen()
        }

        when (throwable) {
            is BottlesException -> showErrorMessage(throwable.message ?: "")
            is BottlesNetworkException -> showErrorMessage(throwable.message ?: "")
            else -> showErrorMessage("예상치 못한 오류가 발생했습니다.")
        }
    }

    private fun showErrorMessage(message: String) {
        postSideEffect(AccountSettingSideEffect.ShowErrorMessage(message = message))
    }

    private fun showErrorScreen() {
        reduce { copy(isError = true) }
    }

    private fun closeErrorScreen() {
        reduce { copy(isError = false) }
    }

    override suspend fun handleIntent(intent: AccountSettingIntent) {
        when (intent) {
            is AccountSettingIntent.ClickConfirmDialogButton -> confirm()
            is AccountSettingIntent.ClickDismissDialogButton -> dismiss()
            is AccountSettingIntent.ClickLogOutButton -> showLogoutDialog()
            is AccountSettingIntent.ClickDeleteUserButton -> showDeleteUserDialog()
            is AccountSettingIntent.ClickBackButton -> navigateToMyPage()
            is AccountSettingIntent.ClickMatchingActiveToggleButton -> changeMatchingActive()
            is AccountSettingIntent.ClickRetryButton -> retry()
        }
    }

    private fun retry() {
        closeErrorScreen()
        initAccountSetting()
    }

    private fun initAccountSetting() {
        launch {
            reduce { copy(accountSettingState = AccountSettingUiState.AccountSettingState.INIT) }
            val profile = getUserProfileUseCase()
            reduce { copy(isMatchActivated = profile.isMatchActivated) }
        }
    }

    private fun confirm() {
        launch {
            when (currentState.dialogType) {
                SettingAlertDialogType.LOG_OUT -> {
                    reduce { copy(accountSettingState = AccountSettingUiState.AccountSettingState.LOG_OUT) }
                    logOutUseCase()
                }

                SettingAlertDialogType.DELETE_USER -> {
                    reduce { copy(accountSettingState = AccountSettingUiState.AccountSettingState.DELETE_USER) }
                    deleteUserUseCase()
                }
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
            reduce { copy(accountSettingState = AccountSettingUiState.AccountSettingState.MATCHING_ACTIVE) }
            updateActivateMatchingUseCase(isActivate = !currentState.isMatchActivated)
            reduce { copy(isMatchActivated = !isMatchActivated) }
        }
    }

}
