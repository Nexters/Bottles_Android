package com.team.bottles.feat.recommendation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.recommendation.mvi.RecommendationSideEffect

@Composable
internal fun RecommendationRoute(
    viewModel: RecommendationViewModel = hiltViewModel(),
    navigateToSandBeach: () -> Unit,
    navigateToRecommendationDetail: (href: String) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is RecommendationSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is RecommendationSideEffect.NavigateToRecommendationDetail -> navigateToRecommendationDetail(sideEffect.href)
            }
        }
    }

    RecommendationScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}