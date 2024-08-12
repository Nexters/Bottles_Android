package com.team.bottles.feat.profile.createprofile

import android.annotation.SuppressLint
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.profile.BuildConfig
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileIntent
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileUiState

@SuppressLint("SetJavaScriptEnabled")
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
            url = BuildConfig.BOTTLES_CREATE_PROFILE_URL + "?accessToken=${uiState.token.accessToken}&refreshToken=${uiState.token.refreshToken}",
            webView = webView,
        )
    }
}