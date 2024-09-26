package com.team.bottles.feat.splash.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SplashSideEffect : UiSideEffect {

    data object NavigateToLoginEndpoint : SplashSideEffect

    data object NavigateToSandBeach : SplashSideEffect

    data object NavigateToOnboarding : SplashSideEffect

    data object NavigateToPlayStore : SplashSideEffect

    data class ShowErrorMessage(val message: String) : SplashSideEffect

}