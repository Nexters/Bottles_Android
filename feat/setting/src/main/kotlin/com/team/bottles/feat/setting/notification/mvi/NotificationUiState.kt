package com.team.bottles.feat.setting.notification.mvi

import com.team.bottles.core.common.UiState

data class NotificationUiState(
    val isFloatingBottle: Boolean = false,
    val isGoodFeelingArrived: Boolean = false,
    val isConversation: Boolean = false,
    val isMarketingResponse: Boolean = false,
    val isError: Boolean = false,
    val notificationState: NotificationState = NotificationState.INIT
) : UiState {

    enum class NotificationState {
        INIT,
        FLOATING_BOTTLE,
        GOOD_FEELING_ARRIVED,
        CONVERSATION,
        MARKETING_RESPONSE,
        ;
    }

}
