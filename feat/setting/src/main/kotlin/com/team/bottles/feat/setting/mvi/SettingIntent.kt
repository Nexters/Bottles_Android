package com.team.bottles.feat.setting.mvi

import com.team.bottles.core.common.UiIntent

sealed interface SettingIntent : UiIntent {

    data object ClickBackButton : SettingIntent

    data object ClickMatchingActiveToggleButton : SettingIntent

    data object ClickLogOutButton : SettingIntent

    data object ClickDeleteUserButton : SettingIntent

    data object ClickFloatingBottleToggleButton : SettingIntent

    data object ClickGoodFeelingArrivedToggleButton : SettingIntent

    data object ClickConversationToggleButton : SettingIntent

    data object ClickMarketingResponseToggleButton : SettingIntent

    data object ClickConfirmDialogButton : SettingIntent

    data object ClickDismissDialogButton : SettingIntent

}