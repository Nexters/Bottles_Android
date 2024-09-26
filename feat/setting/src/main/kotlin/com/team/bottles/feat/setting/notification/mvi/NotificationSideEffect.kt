package com.team.bottles.feat.setting.notification.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface NotificationSideEffect : UiSideEffect {

    data object NavigateToMyPage : NotificationSideEffect

    data class ShowErrorMessage(val message: String) : NotificationSideEffect

}