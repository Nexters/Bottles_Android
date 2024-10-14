package com.team.bottles.feat.recommendation

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.recommendation.mvi.RecommendationIntent
import com.team.bottles.feat.recommendation.mvi.RecommendationUiState

@Composable
internal fun RecommendationScreen(
    uiState: RecommendationUiState,
    onIntent: (RecommendationIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                RecommendationBridge { webAction ->
                    when (webAction) {
                        is RecommendationWebAction.OnWebViewClose -> onIntent(RecommendationIntent.ClickWebCloseButton)
                        is RecommendationWebAction.OnClickBottle -> onIntent(RecommendationIntent.ClickBottleItem(href = webAction.href))
                    }
                },
                RecommendationBridge.NAME
            )
        }
    }

    if (uiState.token.accessToken.isNotEmpty() && uiState.token.refreshToken.isNotEmpty()) {
        BottlesWebView(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding(),
            url = uiState.url,
            webView = webView
        )
    }
}