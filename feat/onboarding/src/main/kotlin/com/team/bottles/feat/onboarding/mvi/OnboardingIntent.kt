package com.team.bottles.feat.onboarding.mvi

import com.team.bottles.core.common.UiIntent

sealed interface OnboardingIntent : UiIntent {

    data object ClickNextButton : OnboardingIntent

    data object ClickBackButton : OnboardingIntent

}