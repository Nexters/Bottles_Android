package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiSideEffect

sealed class LoginSideEffect: UiSideEffect {

    data object NavigateToOnboarding: LoginSideEffect()

    data object NavigateToSandBeach: LoginSideEffect()

}