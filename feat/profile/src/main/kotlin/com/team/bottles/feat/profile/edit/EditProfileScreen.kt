package com.team.bottles.feat.profile.edit

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.profile.edit.mvi.EditProfileIntent
import com.team.bottles.feat.profile.edit.mvi.EditProfileUiState

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun EditProfileScreen(
    uiState: EditProfileUiState,
    onIntent: (EditProfileIntent) -> Unit,
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                EditProfileBridge { webAction ->
                    when (webAction) {
                        is EditProfileWebAction.OnWebViewClose -> onIntent(EditProfileIntent.ClickWebCloseButton)
                    }
                },
                EditProfileBridge.NAME
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
            webView = webView,
        )
    }
}