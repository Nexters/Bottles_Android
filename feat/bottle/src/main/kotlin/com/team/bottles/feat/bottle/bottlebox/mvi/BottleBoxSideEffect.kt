package com.team.bottles.feat.bottle.bottlebox.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface BottleBoxSideEffect : UiSideEffect {

    data class NavigateToPingPong(val bottleId: Long) : BottleBoxSideEffect

}