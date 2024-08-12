package com.team.bottles.feat.login.smslogin

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.login.BuildConfig
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginIntent
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginUiState

@Composable
internal fun SmsLoginScreen(
    uiState: SmsLoginUiState,
    onIntent: (SmsLoginIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                SmsLoginBridge { webAction ->
                    when (webAction) {
                        is SmsLoginWebAction.OnToastOpen -> Toast.makeText(context, webAction.message, Toast.LENGTH_SHORT).show()
                        is SmsLoginWebAction.OnWebViewClose -> onIntent(SmsLoginIntent.ClickWebCloseButton)
                        is SmsLoginWebAction.OnLogin -> onIntent(SmsLoginIntent.ClickWebLoginButton(smsLoginResult = webAction.smsLoginResult))
                    }
                },
                SmsLoginBridge.NAME
            )
        }
    }

    BottlesWebView(
        url = BuildConfig.BOTTLES_SIGNIN_URL,
        webView = webView
    )
}

@Preview(showBackground = true)
@Composable
private fun SmsLoginScreenPreview() {
    BottlesTheme {
        SmsLoginScreen(
            uiState = SmsLoginUiState,
            onIntent = { },
        )
    }
}