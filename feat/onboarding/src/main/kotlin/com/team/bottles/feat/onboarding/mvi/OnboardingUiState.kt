package com.team.bottles.feat.onboarding.mvi

import com.team.bottles.core.common.UiState

data class OnboardingUiState(
    val page: Int = 0
): UiState

