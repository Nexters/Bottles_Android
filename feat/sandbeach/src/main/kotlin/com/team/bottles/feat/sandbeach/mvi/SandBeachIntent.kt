package com.team.bottles.feat.sandbeach.mvi

import com.team.bottles.core.common.UiIntent

sealed interface SandBeachIntent : UiIntent {

    data object ClickCreateIntroductionButton : SandBeachIntent

    data object ClickSandBeach : SandBeachIntent

    data object ClickConfirmButton : SandBeachIntent

    data object ClickRetryButton : SandBeachIntent

}