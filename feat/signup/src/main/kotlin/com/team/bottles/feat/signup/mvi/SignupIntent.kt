package com.team.bottles.feat.signup.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.model.Token

sealed interface SignupIntent : UiIntent {

    data object ClickWebCloseButton : SignupIntent

    data class ClickWebSignupButton(val token: Token) : SignupIntent

    data class ClickWebLink(val href: String) : SignupIntent

}