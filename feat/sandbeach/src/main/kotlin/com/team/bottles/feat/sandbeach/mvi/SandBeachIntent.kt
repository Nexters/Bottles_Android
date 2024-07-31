package com.team.bottles.feat.sandbeach.mvi

import com.team.bottles.core.common.UiIntent

sealed interface SandBeachIntent : UiIntent {

    data object ClickCreateIntroductionButton : SandBeachIntent

    data object ClickBottle : SandBeachIntent

}