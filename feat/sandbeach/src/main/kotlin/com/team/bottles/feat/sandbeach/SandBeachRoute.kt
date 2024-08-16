package com.team.bottles.feat.sandbeach

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.sandbeach.mvi.SandBeachSideEffect

@Composable
internal fun SandBeachRoute(
    viewModel: SandBeachViewModel = hiltViewModel(),
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SandBeachSideEffect.NavigateToIntroduction -> navigateToIntroduction()
                is SandBeachSideEffect.NavigateToArrivedBottle -> navigateToArrivedBottles()
                is SandBeachSideEffect.NavigateToBottleBox -> navigateToBottleBox()
            }
        }
    }

    SandBeachScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )

}