package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface LoginSideEffect: UiSideEffect {

    data object NavigateToOnboarding: LoginSideEffect

    data object NavigateToSandBeach: LoginSideEffect

    data object NavigateToSmsLogin: LoginSideEffect

    data object NavigateToSignup: LoginSideEffect

    data object StartKakaoClient: LoginSideEffect

}