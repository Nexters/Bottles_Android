package com.team.bottles.feat.setting.notification

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.model.NotificationType
import com.team.bottles.core.domain.user.usecase.GetSettingNotificationsUseCase
import com.team.bottles.core.domain.user.usecase.UpdateSettingNotificationUseCase
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
import com.team.bottles.feat.setting.account.mvi.AccountSettingSideEffect
import com.team.bottles.feat.setting.account.mvi.AccountSettingUiState
import com.team.bottles.feat.setting.notification.mvi.NotificationIntent
import com.team.bottles.feat.setting.notification.mvi.NotificationSideEffect
import com.team.bottles.feat.setting.notification.mvi.NotificationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationSettingViewModel @Inject constructor(
    private val getSettingNotificationsUseCase: GetSettingNotificationsUseCase,
    private val updateSettingNotificationUseCase: UpdateSettingNotificationUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<NotificationUiState, NotificationSideEffect, NotificationIntent>(savedStateHandle) {

    init {
        initNotificationSetting()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): NotificationUiState =
        NotificationUiState()

    override suspend fun handleIntent(intent: NotificationIntent) {
        when (intent) {
            is NotificationIntent.ClickBackButton -> navigateToMyPage()
            is NotificationIntent.ClickConversationToggleButton -> changeConversationNotification()
            is NotificationIntent.ClickFloatingBottleToggleButton -> changeFloatingBottleNotification()
            is NotificationIntent.ClickMarketingResponseToggleButton -> changeMarketingResponseNotification()
            is NotificationIntent.ClickGoodFeelingArrivedToggleButton -> changeGoodFeelingArrivedNotification()
            is NotificationIntent.ClickRetryButton -> retry()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        if (currentState.notificationState == NotificationUiState.NotificationState.INIT) {
            showErrorScreen()
        }

        when (throwable) {
            is BottlesException -> showErrorMessage(throwable.message ?: "")
            is BottlesNetworkException -> showErrorMessage(throwable.message ?: "")
            else -> showErrorMessage("예상치 못한 오류가 발생했습니다.")
        }
    }

    private fun retry() {
        closeErrorScreen()
        initNotificationSetting()
    }

    private fun showErrorMessage(message: String) {
        postSideEffect(NotificationSideEffect.ShowErrorMessage(message = message))
    }

    private fun showErrorScreen() {
        reduce { copy(isError = true) }
    }

    private fun closeErrorScreen() {
        reduce { copy(isError = false) }
    }

    private fun navigateToMyPage() {
        postSideEffect(NotificationSideEffect.NavigateToMyPage)
    }

    private fun initNotificationSetting() {
        launch {
            reduce { copy(notificationState = NotificationUiState.NotificationState.INIT) }
            val notifications = getSettingNotificationsUseCase()

            val isConversation = notifications.find { it.notificationType == NotificationType.PING_PONG }?.enabled?: false
            val isMarketingResponse = notifications.find { it.notificationType == NotificationType.MARKETING }?.enabled?: false
            val isFloatingBottle = notifications.find { it.notificationType == NotificationType.DAILY_RANDOM }?.enabled?: false
            val isGoodFeelingArrived = notifications.find { it.notificationType == NotificationType.RECEIVE_LIKE }?.enabled?: false

            reduce {
                copy(
                    isConversation = isConversation,
                    isMarketingResponse = isMarketingResponse,
                    isFloatingBottle = isFloatingBottle,
                    isGoodFeelingArrived = isGoodFeelingArrived
                )
            }
        }
    }

    private fun changeFloatingBottleNotification() {
        launch {
            reduce { copy(notificationState = NotificationUiState.NotificationState.FLOATING_BOTTLE) }
            updateSettingNotificationUseCase(
                notification = Notification(
                    notificationType = NotificationType.DAILY_RANDOM,
                    enabled = !currentState.isFloatingBottle
                )
            )
            reduce { copy(isFloatingBottle = !isFloatingBottle) }
        }
    }

    private fun changeGoodFeelingArrivedNotification() {
        launch {
            reduce { copy(notificationState = NotificationUiState.NotificationState.GOOD_FEELING_ARRIVED) }
            updateSettingNotificationUseCase(
                notification = Notification(
                    notificationType = NotificationType.RECEIVE_LIKE,
                    enabled = !currentState.isGoodFeelingArrived
                )
            )
            reduce { copy(isGoodFeelingArrived = !isGoodFeelingArrived) }
        }
    }

    private fun changeConversationNotification() {
        launch {
            reduce { copy(notificationState = NotificationUiState.NotificationState.CONVERSATION) }
            updateSettingNotificationUseCase(
                notification = Notification(
                    notificationType = NotificationType.PING_PONG,
                    enabled = !currentState.isConversation
                )
            )
            reduce { copy(isConversation = !isConversation) }
        }
    }

    private fun changeMarketingResponseNotification() {
        launch {
            reduce { copy(notificationState = NotificationUiState.NotificationState.MARKETING_RESPONSE) }
            updateSettingNotificationUseCase(
                notification = Notification(
                    notificationType = NotificationType.MARKETING,
                    enabled = !currentState.isMarketingResponse
                )
            )
            reduce { copy(isMarketingResponse = !isMarketingResponse) }
        }
    }

}