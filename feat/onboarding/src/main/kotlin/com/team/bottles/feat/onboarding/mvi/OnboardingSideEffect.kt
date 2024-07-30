package com.team.bottles.feat.onboarding.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface OnboardingSideEffect: UiSideEffect {

    data object NavigateToCreateProfile: OnboardingSideEffect

}