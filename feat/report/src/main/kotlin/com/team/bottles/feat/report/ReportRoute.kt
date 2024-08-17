package com.team.bottles.feat.report

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.report.mvi.ReportSideEffect

@Composable
internal fun ReportRoute(
    viewModel: ReportViewModel = hiltViewModel(),
    navigateToPingPong: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ReportSideEffect.ShowToastMessage -> {
                    Toast.makeText(context, "신고가 완료됐어요", Toast.LENGTH_SHORT).show()
                    navigateToBottleBox()
                }
                is ReportSideEffect.NavigateToPingPong -> navigateToPingPong()
            }
        }
    }

    ReportScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent = intent) }
    )
}