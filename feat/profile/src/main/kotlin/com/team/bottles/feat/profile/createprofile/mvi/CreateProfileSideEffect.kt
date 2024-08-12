package com.team.bottles.feat.profile.createprofile.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface CreateProfileSideEffect : UiSideEffect {

    data object NavigateToMain : CreateProfileSideEffect

    data object NavigateToOnboarding : CreateProfileSideEffect

}