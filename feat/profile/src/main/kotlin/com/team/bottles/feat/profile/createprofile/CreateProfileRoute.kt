package com.team.bottles.feat.profile.createprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileSideEffect

@Composable
internal fun CreateProfileRoute(
    viewModel: CreateProfileViewModel = hiltViewModel(),
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is CreateProfileSideEffect.NavigateToMain -> navigateToSandBeach()
                is CreateProfileSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            }
        }
    }

    CreateProfileScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}