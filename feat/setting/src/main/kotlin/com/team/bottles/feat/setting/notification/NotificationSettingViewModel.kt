package com.team.bottles.feat.setting.notification

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.model.NotificationType
import com.team.bottles.core.domain.user.usecase.GetNotificationPermissionStatusUseCase
import com.team.bottles.core.domain.user.usecase.GetSettingNotificationsUseCase
import com.team.bottles.core.domain.user.usecase.UpdateSettingNotificationUseCase
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
import com.team.bottles.feat.setting.notification.mvi.NotificationIntent
import com.team.bottles.feat.setting.notification.mvi.NotificationSideEffect
import com.team.bottles.feat.setting.notification.mvi.NotificationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationSettingViewModel @Inject constructor(
    private val getSettingNotificationsUseCase: GetSettingNotificationsUseCase,
    private val updateSettingNotificationUseCase: UpdateSettingNotificationUseCase,
    private val getNotificationPermissionStatusUseCase: GetNotificationPermissionStatusUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<NotificationUiState, NotificationSideEffect, NotificationIntent>(savedStateHandle) {

    init {
        initNotificationSetting()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): NotificationUiState =
        NotificationUiState()

    override suspend fun handleIntent(intent: NotificationIntent) {
        when (intent) {
            is NotificationIntent.ClickConversationToggleButton -> {
                changeNotification(
                    notificationState = NotificationUiState.NotificationState.CONVERSATION,
                    notificationType = NotificationType.PING_PONG,
                    currentState = currentState.isConversation,
                    updateState = { reduce { copy(isConversation = !isConversation) } }
                )
            }
            is NotificationIntent.ClickFloatingBottleToggleButton -> {
                changeNotification(
                    notificationState = NotificationUiState.NotificationState.FLOATING_BOTTLE,
                    notificationType = NotificationType.DAILY_RANDOM,
                    currentState = currentState.isFloatingBottle,
                    updateState = { reduce { copy(isFloatingBottle = !isFloatingBottle) } }
                )
            }
            is NotificationIntent.ClickMarketingResponseToggleButton -> {
                changeNotification(
                    notificationState = NotificationUiState.NotificationState.MARKETING_RESPONSE,
                    notificationType = NotificationType.MARKETING,
                    currentState = currentState.isMarketingResponse,
                    updateState = { reduce { copy(isMarketingResponse = !isMarketingResponse) } }
                )
            }
            is NotificationIntent.ClickGoodFeelingArrivedToggleButton -> {
                changeNotification(
                    notificationState = NotificationUiState.NotificationState.GOOD_FEELING_ARRIVED,
                    notificationType = NotificationType.RECEIVE_LIKE,
                    currentState = currentState.isGoodFeelingArrived,
                    updateState = { reduce { copy(isGoodFeelingArrived = !isGoodFeelingArrived) } }
                )
            }
            is NotificationIntent.ClickRetryButton -> retry()
            is NotificationIntent.ClickBackButton -> navigateToMyPage()
            is NotificationIntent.ClickGoSystemSetting -> navigateToSystemSetting()
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

    private fun navigateToSystemSetting() {
        reduce { copy(isShowDialog = false) }
        postSideEffect(NotificationSideEffect.RequireNotificationPermission)
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

    private fun changeNotification(
        notificationState: NotificationUiState.NotificationState,
        notificationType: NotificationType,
        currentState: Boolean,
        updateState: () -> Unit
    ) {
        launch {
            val isPermissionAllowed = getNotificationPermissionStatusUseCase()

            if (isPermissionAllowed) {
                reduce { copy(notificationState = notificationState) }
                updateSettingNotificationUseCase(
                    notification = Notification(
                        notificationType = notificationType,
                        enabled = !currentState
                    )
                )
                updateState.invoke()
            } else {
                reduce { copy(isShowDialog = true) }
            }
        }
    }

}