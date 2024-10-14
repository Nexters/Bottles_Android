package com.team.bottles.feat.like.detail

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.like.detail.mvi.LikeDetailIntent
import com.team.bottles.feat.like.detail.mvi.LikeDetailUiState

@Composable
internal fun LikeDetailScreen(
    uiState: LikeDetailUiState,
    href: String,
    onIntent: (LikeDetailIntent) -> Unit,
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                LikeDetailBridge { webAction ->
                    when (webAction) {
                        is LikeWebAction.OnWebViewClose -> onIntent(LikeDetailIntent.ClickWebCloseButton)
                        is LikeWebAction.OnBottleAccept -> onIntent(LikeDetailIntent.ClickAcceptButton)
                    }
                },
                LikeDetailBridge.NAME
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