package com.team.bottles.feat.bottle.arrivedbottles

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.bottle.BuildConfig
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
            url = BuildConfig.BOTTLES_ARRIVED_BOTTLES_URL,
            webView = webView
        )
    }
}