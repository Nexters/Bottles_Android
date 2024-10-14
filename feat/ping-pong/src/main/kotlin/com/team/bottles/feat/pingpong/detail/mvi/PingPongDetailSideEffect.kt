package com.team.bottles.feat.pingpong.detail.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface PingPongDetailSideEffect : UiSideEffect {

    data object NavigateToPingPong : PingPongDetailSideEffect

    data object OpenKakaoTalkApp : PingPongDetailSideEffect

    data class NavigateToReport(
        val userId: Long,
        val userName: String,
        val userImageUrl: String,
        val userAge: Int
    ) : PingPongDetailSideEffect

}