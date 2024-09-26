package com.team.bottles.feat.setting.account.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface AccountSettingSideEffect : UiSideEffect {

    data object NavigateToMyPage : AccountSettingSideEffect

    data object NavigateToLoginEndpoint : AccountSettingSideEffect

    data class ShowErrorMessage(val message: String) : AccountSettingSideEffect

}