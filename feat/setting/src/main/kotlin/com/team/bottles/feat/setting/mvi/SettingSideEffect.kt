package com.team.bottles.feat.setting.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface SettingSideEffect : UiSideEffect {

    data object NavigateToMyPage : SettingSideEffect

    data object NavigateToLoginEndpoint : SettingSideEffect

}