package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.ui.model.AlertType

data class MyPageUiState(
    val token: Token = Token(),
    val alertType: AlertType = AlertType.LOG_OUT,
    val showDialog: Boolean = false,
) : UiState
