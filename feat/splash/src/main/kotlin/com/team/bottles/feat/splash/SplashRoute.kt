package com.team.bottles.feat.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SplashSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is SplashSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
                is SplashSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            }
        }
    }

    SplashScreen()
}
