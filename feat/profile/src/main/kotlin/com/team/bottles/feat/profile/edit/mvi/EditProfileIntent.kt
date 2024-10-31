package com.team.bottles.feat.profile.edit.mvi

import com.team.bottles.core.common.UiIntent

sealed interface EditProfileIntent : UiIntent {

    data object ClickWebCloseButton : EditProfileIntent

    data class ShowToastMessage(val message: String) : EditProfileIntent

}