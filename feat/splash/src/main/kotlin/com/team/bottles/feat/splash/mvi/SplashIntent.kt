package com.team.bottles.feat.splash.mvi

import com.team.bottles.core.common.UiIntent

sealed interface SplashIntent : UiIntent {

    data object ClickConfirmButton : SplashIntent

    data object ClickRetryButton : SplashIntent

}