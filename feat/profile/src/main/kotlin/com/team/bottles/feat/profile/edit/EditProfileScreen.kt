package com.team.bottles.feat.profile.edit

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.profile.BuildConfig
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
            url = BuildConfig.BOTTLES_PROFILE_EDIT_URL +
                    "?accessToken=${uiState.token.accessToken}" +
                    "&refreshToken=${uiState.token.refreshToken}" +
                    "&device=${BuildConfig.DEVICE}" +
                    "&version=${BuildConfig.APP_VERSION}",
            webView = webView,
        )
    }
}