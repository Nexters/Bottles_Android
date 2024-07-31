package com.team.bottles.feat.profile.introduction.mvi

import com.team.bottles.core.common.UiIntent

sealed interface IntroductionIntent: UiIntent {

    data object ClickBackButton: IntroductionIntent

}