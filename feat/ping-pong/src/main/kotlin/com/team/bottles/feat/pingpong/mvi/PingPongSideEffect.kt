package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface PingPongSideEffect : UiSideEffect {

    data object NavigateToBottleBox : PingPongSideEffect

}