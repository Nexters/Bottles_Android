package com.team.bottles.feat.report

import ReportNavigator
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.feat.report.mvi.ReportIntent
import com.team.bottles.feat.report.mvi.ReportSideEffect
import com.team.bottles.feat.report.mvi.ReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel<ReportUiState, ReportSideEffect, ReportIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): ReportUiState {
        val savedState = savedStateHandle.toRoute<ReportNavigator>()
        return ReportUiState(
            userId = savedState.userId,
            userImageUrl = savedState.userImageUrl,
            userName = savedState.userName,
            userAge = savedState.userAge
        )
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: ReportIntent) {
        when (intent) {
            is ReportIntent.ClickBackButton -> navigateToPingPong()
            is ReportIntent.ClickCompleteButton -> showDialog()
            is ReportIntent.ChangeReportContents -> changeReportContents(contents = intent.contents)
            is ReportIntent.ClickDialogCancel -> reduce { copy(showDialog = false) }
            is ReportIntent.ClickDialogConfirm -> report()
            is ReportIntent.FocusedTextField -> changeTextFieldState(isFocused = intent.isFocused)
            is ReportIntent.ClickDeleteButton -> reduce { copy(reportContents = "") }
        }
    }

    private fun report() {
        // TODO : 신고 UseCase 연결 + showDialog false로 변경
    }

    private fun changeTextFieldState(isFocused: Boolean) {
        reduce {
            copy(
                reportContentsState = if (isFocused) {
                    BottlesTextFieldState.Focused
                } else {
                    if (currentState.reportContents.isEmpty()) {
                        BottlesTextFieldState.Enabled
                    } else {
                        BottlesTextFieldState.Active
                    }
                }
            )
        }
    }

    private fun changeReportContents(contents: String) {
        reduce {
            copy(
                reportContents = contents,
            )
        }
    }

    private fun showDialog() {
        if (currentState.reportContents.isNotEmpty()) {
            reduce { copy(showDialog = true) }
        }
    }

    private fun navigateToPingPong() {

    }

}