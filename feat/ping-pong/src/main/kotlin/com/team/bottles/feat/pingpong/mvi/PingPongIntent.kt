package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiIntent

sealed interface PingPongIntent : UiIntent {

    data object ClickBackButton : PingPongIntent

    data object ClickReportButton : PingPongIntent

    data class ClickTabButton(val tab: PingPongTab) : PingPongIntent

    data object ClickConversationFinishButton : PingPongIntent

    data object ClickCloseAlert : PingPongIntent

    data object ClickConfirmAlert : PingPongIntent

}