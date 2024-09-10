package com.team.bottles.feat.setting.notification

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.model.NotificationType
import com.team.bottles.core.domain.user.usecase.GetSettingNotificationsUseCase
import com.team.bottles.core.domain.user.usecase.UpdateSettingNotificationUseCase
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
        launch {
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

    override fun createInitialState(savedStateHandle: SavedStateHandle): NotificationUiState =
        NotificationUiState()

    override suspend fun handleIntent(intent: NotificationIntent) {
        when (intent) {
            is NotificationIntent.ClickBackButton -> navigateToMyPage()
            is NotificationIntent.ClickConversationToggleButton -> changeConversationNotification()
            is NotificationIntent.ClickFloatingBottleToggleButton -> changeFloatingBottleNotification()
            is NotificationIntent.ClickMarketingResponseToggleButton -> changeMarketingResponseNotification()
            is NotificationIntent.ClickGoodFeelingArrivedToggleButton -> changeGoodFeelingArrivedNotification()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToMyPage() {
        postSideEffect(NotificationSideEffect.NavigateToMyPage)
    }

    private fun changeFloatingBottleNotification() {
        launch {
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