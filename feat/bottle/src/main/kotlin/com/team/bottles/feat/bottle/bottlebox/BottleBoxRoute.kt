package com.team.bottles.feat.bottle.bottlebox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxSideEffect

@Composable
internal fun BottleBoxRoute(
    viewModel: BottleBoxViewModel = hiltViewModel(),
    navigateToBottle: (Long) -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is BottleBoxSideEffect.NavigateToBottle -> navigateToBottle(sideEffect.bottleId)
            }
        }
    }

    BottleBoxScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}