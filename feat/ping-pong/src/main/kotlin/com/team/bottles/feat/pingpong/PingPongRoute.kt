package com.team.bottles.feat.pingpong

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect

@Composable
internal fun PingPongRoute(
    viewModel: PingPongViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navigateToPingPongDetail: (Long) -> Unit,
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.initPingPongList()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is PingPongSideEffect.NavigateToPingPongDetail -> navigateToPingPongDetail(sideEffect.bottleId)
                is PingPongSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                is PingPongSideEffect.NavigateToSandBeach -> navigateToSandBeach()
            }
        }
    }

    PingPongScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}