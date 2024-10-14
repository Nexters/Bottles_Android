package com.team.bottles.feat.profile.createprofile

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
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileIntent
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileUiState

@Composable
internal fun CreateProfileScreen(
    uiState: CreateProfileUiState,
    onIntent: (CreateProfileIntent) -> Unit,
) {
    val context = LocalContext.current
    val webView = remember { WebView(context).apply {
        addJavascriptInterface(
            CreateProfileBridge { webAction ->
                when (webAction) {
                    is CreateProfileWebAction.OnToastOpen -> Toast.makeText(context, webAction.message, Toast.LENGTH_SHORT).show()
                    is CreateProfileWebAction.OnWebViewClose -> onIntent(CreateProfileIntent.ClickWebCloseButton)
                    is CreateProfileWebAction.OnCreateComplete -> onIntent(CreateProfileIntent.ClickWebCreateProfileButton)
                }
            },
            CreateProfileBridge.NAME
        )
    } }

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