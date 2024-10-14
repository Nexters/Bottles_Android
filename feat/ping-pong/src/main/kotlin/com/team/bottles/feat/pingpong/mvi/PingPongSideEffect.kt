package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface PingPongSideEffect : UiSideEffect {

    data class NavigateToPingPongDetail(val bottleId: Long) : PingPongSideEffect

    data class ShowErrorMessage(val message: String) : PingPongSideEffect

    data object NavigateToSandBeach : PingPongSideEffect

}