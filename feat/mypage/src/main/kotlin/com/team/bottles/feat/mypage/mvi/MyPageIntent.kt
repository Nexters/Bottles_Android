package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiIntent

sealed interface MyPageIntent : UiIntent {

    data object ClickWebLogOutButton : MyPageIntent

    data object ClickWebDeleteUserButton : MyPageIntent

    data object ClickDialogDeleteUserButton : MyPageIntent

    data object ClickDialogLogOutButton : MyPageIntent

    data object ClickCancel : MyPageIntent

}