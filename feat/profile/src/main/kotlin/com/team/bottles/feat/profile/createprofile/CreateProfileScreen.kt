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
    onIntent: (CreateProfileIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    BottlesWebView(
        url = BuildConfig.BOTTLES_CREATE_PROFILE_URL + "?accessToken=${uiState.token.accessToken}&refreshToken=${uiState.token.refreshToken}",
        bridge = {
            CreateProfileBridge { webEvent ->
                when (webEvent) {
                    is CreateProfileWebAction.OnToastOpen ->
                        Toast.makeText(context, webEvent.message, Toast.LENGTH_SHORT).show()
                    is CreateProfileWebAction.OnWebViewClose ->
                        onIntent(CreateProfileIntent.ClickWebCloseButton)
                    is CreateProfileWebAction.OnCreateComplete ->
                        onIntent(CreateProfileIntent.ClickWebCompleteButton(token = webEvent.token))
                }
            }
        },
        bridgeName = CreateProfileBridge.NAME,
        webView = webView
    )
}