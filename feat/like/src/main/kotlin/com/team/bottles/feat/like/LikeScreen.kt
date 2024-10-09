package com.team.bottles.feat.like

import android.webkit.WebView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.like.mvi.LikeIntent
import com.team.bottles.feat.like.mvi.LikeUiState

@Composable
internal fun LikeScreen(
    uiState: LikeUiState,
    onIntent: (LikeIntent) -> Unit,
    innerPadding: PaddingValues,
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                LikeBridge { webAction ->
                    when (webAction) {
                        is LikeWebAction.OnClickBottle -> onIntent(LikeIntent.ClickBottleItem)
                    }
                },
                LikeBridge.NAME
            )
        }
    }

    if (uiState.token.accessToken.isNotEmpty() && uiState.token.refreshToken.isNotEmpty()) {
        BottlesWebView(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            url = BuildConfig.BOTTLES_LIKE_URL +
                    "?accessToken=${uiState.token.accessToken}" +
                    "&refreshToken=${uiState.token.refreshToken}" +
                    "&device=${BuildConfig.DEVICE}" +
                    "&version=${BuildConfig.APP_VERSION}",
            webView = webView
        )
    }
}