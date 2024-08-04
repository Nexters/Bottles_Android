package com.team.bottles.feat.signup.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.model.Token

sealed interface SignupIntent : UiIntent {

    data object ClickWebCloseButton : SignupIntent

    data class ClickSignupButton(val token: Token) : SignupIntent

    data class LoadWebPage(val canGoBack: Boolean) : SignupIntent

}