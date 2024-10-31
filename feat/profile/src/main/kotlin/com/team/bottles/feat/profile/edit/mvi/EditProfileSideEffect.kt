package com.team.bottles.feat.profile.edit.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface EditProfileSideEffect : UiSideEffect {

    data object NavigateToMyPage : EditProfileSideEffect

    data class ShowToastMessage(val message: String) : EditProfileSideEffect

}