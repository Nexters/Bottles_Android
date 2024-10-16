package com.team.bottles.feat.like

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.feat.like.components.LikeWebView
import com.team.bottles.feat.like.mvi.LikeIntent
import com.team.bottles.feat.like.mvi.LikeUiState

@Composable
internal fun LikeScreen(
    uiState: LikeUiState,
    onIntent: (LikeIntent) -> Unit,
    innerPadding: PaddingValues,
) {
    if (uiState.token.accessToken.isNotEmpty() && uiState.token.refreshToken.isNotEmpty()) {
        LikeWebView(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .systemBarsPadding(),
            url = uiState.url,
            onClickBottle = { href -> onIntent(LikeIntent.ClickBottleItem(href = href)) },
        )
    }
}