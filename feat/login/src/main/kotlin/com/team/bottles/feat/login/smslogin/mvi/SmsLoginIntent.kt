package com.team.bottles.feat.login.smslogin.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.feat.login.smslogin.SmsLoginWebResult

sealed interface SmsLoginIntent : UiIntent {

    data object ClickWebCloseButton : SmsLoginIntent

    data class ClickWebLoginButton(val smsLoginResult: SmsLoginWebResult) : SmsLoginIntent

}