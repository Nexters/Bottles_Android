package com.team.bottles.feat.login.smslogin.mvi

import com.team.bottles.core.common.UiIntent

sealed interface SmsLoginIntent :UiIntent {

    data object ClickWebBackButton: SmsLoginIntent // webview에서 뒤로가기 버튼 클릭

    data object ClickWebCompleteButton: SmsLoginIntent // webview에서 확인 버튼 클릭

}