package com.team.bottles.feat.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.onboarding.mvi.OnboardingSideEffect

@Composable
internal fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToCreateProfile: () -> Unit,
    navigateToLoginEndpoint: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is OnboardingSideEffect.NavigateToCreateProfile -> navigateToCreateProfile()
                is OnboardingSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
            }
        }
    }

    OnboardingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent)}
    )
}
