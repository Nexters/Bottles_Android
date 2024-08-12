package com.team.bottles.feat.bottle.arrivedbottles.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface ArrivedBottlesSideEffect : UiSideEffect {

    data object NavigateToSandBeach : ArrivedBottlesSideEffect

    data object NavigateToBottleBox : ArrivedBottlesSideEffect

}