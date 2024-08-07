package com.team.bottles.feat.profile.createprofile.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.model.Token

sealed interface CreateProfileIntent : UiIntent {

    data object ClickWebCloseButton : CreateProfileIntent

    data class ClickWebCompleteButton(val token: Token) : CreateProfileIntent

}