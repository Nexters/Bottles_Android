package com.team.bottles.feat.like

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.like.mvi.LikeSideEffect

@Composable
internal fun LikeRoute(
    viewModel: LikeViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    navigateToLikeDetail: (href: String) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is LikeSideEffect.NavigateToLikeDetail -> navigateToLikeDetail(sideEffect.href)
            }
        }
    }

    LikeScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}