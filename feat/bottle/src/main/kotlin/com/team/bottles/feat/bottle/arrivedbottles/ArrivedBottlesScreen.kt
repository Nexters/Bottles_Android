package com.team.bottles.feat.bottle.arrivedbottles

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesIntent
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesUiState

@Composable
internal fun ArrivedBottlesScreen(
    uiState: ArrivedBottlesUiState,
    onIntent: (ArrivedBottlesIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                ArrivedBottlesBridge { webAction ->
                    when (webAction) {
                        is ArrivedBottlesWebAction.OnWebViewClose -> onIntent(ArrivedBottlesIntent.ClickWebCloseButton)
                        is ArrivedBottlesWebAction.OnBottleAccept -> onIntent(ArrivedBottlesIntent.ClickWebBottleAcceptButton)
                        is ArrivedBottlesWebAction.OnToastOpen -> Toast.makeText(context, webAction.message, Toast.LENGTH_SHORT).show()
                    }
                },
                ArrivedBottlesBridge.NAME
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