package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiIntent

sealed class LoginIntent: UiIntent {

    data object ClickLoginButton: LoginIntent()

}