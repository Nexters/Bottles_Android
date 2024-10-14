package com.team.bottles.feat.recommendation.detail

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailIntent
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailUiState

@Composable
internal fun RecommendationDetailScreen(
    href: String,
    uiState: RecommendationDetailUiState,
    onIntent: (RecommendationDetailIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                RecommendationDetailBridge { webAction ->
                    when (webAction) {
                        is RecommendationDetailWebAction.OnWebViewClose ->
                            onIntent(RecommendationDetailIntent.ClickWebCloseButton)
                        is RecommendationDetailWebAction.OnToastMessage ->
                            onIntent(RecommendationDetailIntent.ClickLeaveOrAcceptButton(toastMessage = webAction.message))
                    }
                },
                RecommendationDetailBridge.NAME
            )
        }
    }

    BottlesWebView(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
        url = href,
        webView = webView
    )
}