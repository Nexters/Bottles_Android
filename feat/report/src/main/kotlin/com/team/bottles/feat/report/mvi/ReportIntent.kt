package com.team.bottles.feat.report.mvi

import com.team.bottles.core.common.UiIntent

sealed interface ReportIntent : UiIntent {

    data object ClickBackButton : ReportIntent

    data object ClickDialogConfirm : ReportIntent

    data object ClickDialogCancel : ReportIntent

    data object ClickCompleteButton : ReportIntent

    data class ChangeReportContents(val contents: String) : ReportIntent

    data class FocusedTextField(val isFocused: Boolean) : ReportIntent

    data object ClickDeleteButton : ReportIntent

}