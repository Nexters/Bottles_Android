package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.KakaoClinetResult

sealed class LoginIntent : UiIntent {

    data class ClickKakaoLoginButton(val accessToken: KakaoClinetResult) : LoginIntent()

}