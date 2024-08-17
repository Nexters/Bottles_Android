package com.team.bottles.feat.report.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState

data class ReportUiState(
    val showDialog: Boolean = false,
    val userImageUrl: String = "",
    val userName: String = "",
    val userAge: Int = 0,
    val reportContents: String = "",
    val reportContentsState: BottlesTextFieldState = BottlesTextFieldState.Enabled
): UiState