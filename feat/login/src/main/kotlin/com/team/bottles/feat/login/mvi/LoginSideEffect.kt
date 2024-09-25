package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface LoginSideEffect : UiSideEffect {

    data object NavigateToOnboarding : LoginSideEffect

    data object NavigateToCreateProfile : LoginSideEffect

    data object NavigateToSandBeach : LoginSideEffect

    data object StartKakaoClient : LoginSideEffect

    data class ShowErrorMessage(val message: String) : LoginSideEffect

}