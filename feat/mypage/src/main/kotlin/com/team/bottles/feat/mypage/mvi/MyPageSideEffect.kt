package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface MyPageSideEffect : UiSideEffect {

    data object NavigateToLoginEndPoint : MyPageSideEffect

}