package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiIntent

sealed interface MyPageIntent : UiIntent {

    data object ClickEditProfile : MyPageIntent

    data object ClickUpdateBlockContact : MyPageIntent

    data object ClickSettingNotification : MyPageIntent

    data object ClickAccountManagement : MyPageIntent

    data object ClickUpdateAppVersion : MyPageIntent

    data object ClickAsk : MyPageIntent

    data object ClickTermsOfUse : MyPageIntent

    data object ClickPolicy : MyPageIntent

    data object ClickConfirmBlockContacts : MyPageIntent

    data object CloseBlockContactsDialog : MyPageIntent

    data object ClickConfirmContactAccessButton : MyPageIntent

    data object ClickRetry : MyPageIntent

}