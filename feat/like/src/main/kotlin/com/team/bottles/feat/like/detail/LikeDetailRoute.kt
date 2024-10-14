package com.team.bottles.feat.like.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.like.detail.mvi.LikeDetailSideEffect

@Composable
internal fun LikeDetailRoute(
    viewModel: LikeDetailViewModel = hiltViewModel(),
    href: String,
    navigateToLikeDetail: () -> Unit,
    navigateToQna: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is LikeDetailSideEffect.NavigateToLikeDetail -> navigateToLikeDetail()
                is LikeDetailSideEffect.NavigateToQna -> navigateToQna()
            }
        }
    }

    LikeDetailScreen(
        uiState = uiState,
        href = href,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}