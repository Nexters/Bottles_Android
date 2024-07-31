package com.team.bottles.feat.bottle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesSideEffect
import com.team.bottles.feat.bottle.mvi.BottleSideEffect

@Composable
internal fun BottleRoute(
    viewModel: BottleViewModel = hiltViewModel(),
    navigateToBottleBox: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is BottleSideEffect.NavigateToBottleBox -> navigateToBottleBox()
            }
        }
    }

    BottleScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}