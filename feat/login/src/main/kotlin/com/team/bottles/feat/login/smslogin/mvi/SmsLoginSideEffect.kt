package com.team.bottles.feat.login.smslogin.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SmsLoginSideEffect :UiSideEffect {

    data object NavigateToLoginEndPoint: SmsLoginSideEffect

    data object NavigateToSandBeach: SmsLoginSideEffect

    data object NavigateToOnboarding: SmsLoginSideEffect

}