package com.team.bottles.feat.signup.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.domain.auth.KakaoClientResult

sealed interface SignupIntent : UiIntent {

    data object ClickWebBackButton: SignupIntent // webview에서 뒤로가기 버튼 클릭

    data object ClickWebCompleteButton: SignupIntent // webview에서 확인 버튼 클릭

}