package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface MyPageSideEffect : UiSideEffect {

    data object NavigateToEditProfile : MyPageSideEffect

    data object NavigateToSettingNotification : MyPageSideEffect

    data object NavigateToSettingAccountManagement : MyPageSideEffect

    data object NavigateToKakaoBusinessChannel : MyPageSideEffect

    data object NavigateToTermsOfUseNotion : MyPageSideEffect

    data object NavigateToPolicyNotion : MyPageSideEffect

    data object CheckContactPermission : MyPageSideEffect

    data object NavigateToPlayStore : MyPageSideEffect

    data object CompleteBlockContacts : MyPageSideEffect

}