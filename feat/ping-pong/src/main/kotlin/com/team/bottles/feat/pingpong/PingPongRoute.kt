package com.team.bottles.feat.pingpong

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect

@Composable
internal fun PingPongRoute(
    viewModel: PingPongViewModel = hiltViewModel(),
    navigateToBottleBox: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is PingPongSideEffect.NavigateToBottleBox -> navigateToBottleBox()
            }
        }
    }

    PingPongScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}