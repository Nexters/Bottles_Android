package com.team.bottles.feat.profile.introduction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.profile.introduction.mvi.IntroductionSideEffect

@Composable
internal fun IntroductionRoute(
    viewModel: IntroductionViewModel = hiltViewModel(),
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is IntroductionSideEffect.NavigateToSandBeach -> navigateToSandBeach()
            }
        }
    }

    IntroductionScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )

}