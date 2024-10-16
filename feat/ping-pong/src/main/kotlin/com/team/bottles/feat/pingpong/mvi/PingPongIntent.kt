package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.ui.model.Bottle

sealed interface PingPongIntent : UiIntent {

    data class ClickBottleItem(val bottle: Bottle) : PingPongIntent

    data object ClickRetryButton : PingPongIntent

    data object ClickGoToSandBeach : PingPongIntent

}