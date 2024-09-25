package com.team.bottles.feat.splash

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.team.bottles.feat.splash.mvi.SplashSideEffect

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SplashSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                is SplashSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is SplashSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
                is SplashSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                is SplashSideEffect.NavigateToPlayStore -> {
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

    SplashScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent = intent) }
    )
}
