package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.login.ThirdPartyAccessToken

sealed class LoginIntent : UiIntent {

    data class ClickKakaoLoginButton(val accessToken: ThirdPartyAccessToken) : LoginIntent()

}