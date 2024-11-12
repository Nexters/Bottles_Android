package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.ui.model.Bottle

sealed class PingPongUiState : UiState {

    data class Success(val bottles: List<Bottle> = emptyList()) : PingPongUiState()

    data object Error : PingPongUiState()

    data object Loading : PingPongUiState()

}
