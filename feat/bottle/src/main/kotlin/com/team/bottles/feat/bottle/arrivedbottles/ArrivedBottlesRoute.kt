package com.team.bottles.feat.bottle.arrivedbottles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesSideEffect

@Composable
internal fun ArrivedBottlesRoute(
    viewModel: ArrivedBottlesViewModel = hiltViewModel(),
    navigateToSandBeach: () -> Unit,
    navigateToBottleBox: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ArrivedBottlesSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is ArrivedBottlesSideEffect.NavigateToBottleBox -> navigateToBottleBox()
            }
        }
    }

    ArrivedBottlesScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}