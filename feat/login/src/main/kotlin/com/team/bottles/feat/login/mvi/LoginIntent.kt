package com.team.bottles.feat.login.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.KakaoClientResult

sealed interface LoginIntent : UiIntent {

    data object ClickKakaoLoginButton : LoginIntent

    data object ClickSmsLoginButton: LoginIntent

    data class KakaoLogin(val kakaoClientResult: KakaoClientResult): LoginIntent

}