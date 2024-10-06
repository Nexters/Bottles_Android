package com.team.bottles.feat.sandbeach

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import com.team.bottles.feat.sandbeach.mvi.SandBeachSideEffect

@Composable
internal fun SandBeachRoute(
    viewModel: SandBeachViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.initSandBeach()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SandBeachSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                is SandBeachSideEffect.NavigateToIntroduction -> navigateToIntroduction()
                is SandBeachSideEffect.NavigateToArrivedBottle -> navigateToArrivedBottles()
                is SandBeachSideEffect.NavigateToBottleBox -> navigateToBottleBox()
                is SandBeachSideEffect.NavigateToPlayStore -> {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.team.bottles&hl=ko"))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.team.bottles&hl=ko"))
                        context.startActivity(webIntent)
                    }
                }
            }
        }
    }

    SandBeachScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )

}