package com.team.bottles.feat.sandbeach.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SandBeachSideEffect : UiSideEffect {

    data object NavigateToIntroduction : SandBeachSideEffect

    data object NavigateToArrivedBottle : SandBeachSideEffect

}