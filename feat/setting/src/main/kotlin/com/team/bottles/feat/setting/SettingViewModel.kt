package com.team.bottles.feat.setting

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCase
import com.team.bottles.core.domain.auth.usecase.LogOutUseCase
import com.team.bottles.feat.setting.mvi.SettingAlertDialogType
import com.team.bottles.feat.setting.mvi.SettingIntent
import com.team.bottles.feat.setting.mvi.SettingSideEffect
import com.team.bottles.feat.setting.mvi.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SettingUiState, SettingSideEffect, SettingIntent>(
    savedStateHandle
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): SettingUiState =
        SettingUiState()

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SettingIntent) {
        when (intent) {
            is SettingIntent.ClickConfirmDialogButton -> confirm()
            is SettingIntent.ClickDismissDialogButton -> dismiss()
            is SettingIntent.ClickLogOutButton -> showLogoutDialog()
            is SettingIntent.ClickDeleteUserButton -> showDeleteUserDialog()
            is SettingIntent.ClickBackButton -> navigateToMyPage()
            is SettingIntent.ClickMatchingActiveToggleButton -> changeMatchingActive()
            is SettingIntent.ClickFloatingBottleToggleButton -> changeFloatingBottleNotification()
            is SettingIntent.ClickGoodFeelingArrivedToggleButton -> changeGoodFeelingArrivedNotification()
            is SettingIntent.ClickConversationToggleButton -> changeConversationNotification()
            is SettingIntent.ClickMarketingResponseToggleButton -> changeMarketingResponseNotification()
        }
    }

    private fun confirm() {
        launch {
            when (currentState.dialogType) {
                SettingAlertDialogType.LOG_OUT -> logOutUseCase()
                SettingAlertDialogType.DELETE_USER -> deleteUserUseCase()
            }
            reduce { copy(showDialog = false) }
            postSideEffect(SettingSideEffect.NavigateToLoginEndpoint)
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
        postSideEffect(SettingSideEffect.NavigateToMyPage)
    }

    private fun changeMatchingActive() {
        launch {
            // TODO : 매칭 활성화 on/off 로직
            reduce { copy(isMatchingActive = !isMatchingActive) }
        }
    }

    private fun changeFloatingBottleNotification() {
        launch {
            // TODO : 떠다니는 보틀 알림 on/off 로직
            reduce { copy(isFloatingBottle = !isFloatingBottle) }
        }
    }

    private fun changeGoodFeelingArrivedNotification() {
        launch {
            // TODO : 호가 도착 알림 on/off 로직
            reduce { copy(isGoodFeelingArrived = !isGoodFeelingArrived) }
        }
    }

    private fun changeConversationNotification() {
        launch {
            // TODO : 대화 알림 on/off 로직
            reduce { copy(isConversation = !isConversation) }
        }
    }

    private fun changeMarketingResponseNotification() {
        launch {
            // TODO : 마케팅 수신 동의 알림 on/off 로직
            reduce { copy(isMarketingResponse = !isMarketingResponse) }
        }
    }

}
