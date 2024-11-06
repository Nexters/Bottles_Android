package com.team.bottles.feat.profile.introduction.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface IntroductionSideEffect : UiSideEffect {

    data object NavigateToSandBeach : IntroductionSideEffect

    data class ShowToastMessage(val message: String) : IntroductionSideEffect

}