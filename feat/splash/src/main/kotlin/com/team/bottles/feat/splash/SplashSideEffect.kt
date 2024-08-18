package com.team.bottles.feat.splash

sealed interface SplashSideEffect {

    data object NavigateToLoginEndpoint : SplashSideEffect

    data object NavigateToSandBeach : SplashSideEffect

    data object NavigateToOnboarding : SplashSideEffect

}