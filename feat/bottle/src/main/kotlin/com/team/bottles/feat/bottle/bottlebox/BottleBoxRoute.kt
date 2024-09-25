package com.team.bottles.feat.bottle.bottlebox

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxSideEffect

@Composable
internal fun BottleBoxRoute(
    viewModel: BottleBoxViewModel = hiltViewModel(),
    navigateToPingPong: (Long) -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is BottleBoxSideEffect.NavigateToPingPong -> navigateToPingPong(sideEffect.bottleId)
                is BottleBoxSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    BottleBoxScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}