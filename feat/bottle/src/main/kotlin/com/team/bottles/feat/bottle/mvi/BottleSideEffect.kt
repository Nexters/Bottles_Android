package com.team.bottles.feat.bottle.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface BottleSideEffect : UiSideEffect {

    data object NavigateToBottleBox : BottleSideEffect

}