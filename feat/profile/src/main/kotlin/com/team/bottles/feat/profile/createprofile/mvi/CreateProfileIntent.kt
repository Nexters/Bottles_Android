package com.team.bottles.feat.profile.createprofile.mvi

import com.team.bottles.core.common.UiIntent

sealed interface CreateProfileIntent: UiIntent {

    data object ClickNextButton: CreateProfileIntent

}