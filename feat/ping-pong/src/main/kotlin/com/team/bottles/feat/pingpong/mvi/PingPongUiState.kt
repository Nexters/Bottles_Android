package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.ui.model.Bottle

data class PingPongUiState(
    val bottles: List<Bottle> = emptyList(),
    val isError: Boolean = false,
) : UiState
