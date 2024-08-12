package com.team.bottles.feat.signup.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SignupSideEffect : UiSideEffect {

    data object NavigateToLoginEndPoint : SignupSideEffect

    data object NavigateToSandBeach : SignupSideEffect

    data class OpenLink(val href: String) : SignupSideEffect

}