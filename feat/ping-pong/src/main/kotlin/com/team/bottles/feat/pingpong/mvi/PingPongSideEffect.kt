package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface PingPongSideEffect : UiSideEffect {

    data object NavigateToBottleBox : PingPongSideEffect

    data object OpenKakaoTalkApp : PingPongSideEffect

    data class NavigateToReport(
        val userId: Long,
        val userName: String,
        val userImageUrl: String,
        val userAge: Int
    ) : PingPongSideEffect

}