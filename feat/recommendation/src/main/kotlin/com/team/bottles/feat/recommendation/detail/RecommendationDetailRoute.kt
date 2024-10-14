package com.team.bottles.feat.recommendation.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailSideEffect

@Composable
internal fun RecommendationDetailRoute(
    viewModel: RecommendationDetailViewModel = hiltViewModel(),
    navigateToRecommendation: () -> Unit,
    href: String,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is RecommendationDetailSideEffect.NavigateToRecommendation -> navigateToRecommendation()
                is RecommendationDetailSideEffect.ShowToastMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    RecommendationDetailScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
        href = href
    )
}