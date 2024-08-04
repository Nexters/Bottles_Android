package com.team.bottles.feat.signup.mvi

import com.team.bottles.core.common.UiState

data class SignupUiState(
    val isWebPageCanGoBack: Boolean = false
) : UiState
