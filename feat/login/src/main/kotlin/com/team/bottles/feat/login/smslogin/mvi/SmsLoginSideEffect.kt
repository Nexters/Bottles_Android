package com.team.bottles.feat.login.smslogin.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SmsLoginSideEffect :UiSideEffect {

    data object NavigateToLogin: SmsLoginSideEffect

    data object NavigateToSandBeach: SmsLoginSideEffect

}