package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiState

data class PingPongUiState(
    val text: String = ""
) : UiState