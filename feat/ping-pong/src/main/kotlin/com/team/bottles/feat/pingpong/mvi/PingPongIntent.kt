package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiIntent

sealed interface PingPongIntent : UiIntent {

    data object ClickBackButton : PingPongIntent

}