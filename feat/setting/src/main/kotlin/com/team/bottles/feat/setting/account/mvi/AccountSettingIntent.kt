package com.team.bottles.feat.setting.account.mvi

import com.team.bottles.core.common.UiIntent

sealed interface AccountSettingIntent : UiIntent {

    data object ClickBackButton : AccountSettingIntent

    data object ClickMatchingActiveToggleButton : AccountSettingIntent

    data object ClickLogOutButton : AccountSettingIntent

    data object ClickDeleteUserButton : AccountSettingIntent

    data object ClickConfirmDialogButton : AccountSettingIntent

    data object ClickDismissDialogButton : AccountSettingIntent

}