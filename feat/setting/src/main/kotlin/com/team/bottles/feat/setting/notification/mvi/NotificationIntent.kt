package com.team.bottles.feat.setting.notification.mvi

import com.team.bottles.core.common.UiIntent

sealed interface NotificationIntent : UiIntent {

    data object ClickBackButton : NotificationIntent

    data object ClickFloatingBottleToggleButton : NotificationIntent

    data object ClickGoodFeelingArrivedToggleButton : NotificationIntent

    data object ClickConversationToggleButton : NotificationIntent

    data object ClickMarketingResponseToggleButton : NotificationIntent

    data object ClickRetryButton : NotificationIntent

}