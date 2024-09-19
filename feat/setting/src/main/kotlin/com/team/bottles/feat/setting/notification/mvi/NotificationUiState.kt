package com.team.bottles.feat.setting.notification.mvi

import com.team.bottles.core.common.UiState

data class NotificationUiState(
    val isFloatingBottle: Boolean = false,
    val isGoodFeelingArrived: Boolean = false,
    val isConversation: Boolean = false,
    val isMarketingResponse: Boolean = false,
) : UiState
