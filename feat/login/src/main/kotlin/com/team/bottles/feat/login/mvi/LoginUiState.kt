package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiState

data class LoginUiState(
    val text: String = "온보딩으로"
): UiState