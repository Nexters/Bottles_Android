package com.team.bottles.feat.signup

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.signup.mvi.SignupIntent
import com.team.bottles.feat.signup.mvi.SignupUiState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onIntent: (SignupIntent) -> Unit,
) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    BackHandler {
        if (uiState.isWebPageCanGoBack){
            webView.goBack()
        }
        else {
            onIntent(SignupIntent.ClickWebCloseButton)
        }
    }

    DisposableEffect(webView) {
        webView.webViewClient = object : WebViewClient() {
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)

                onIntent(SignupIntent.LoadWebPage(canGoBack = view?.canGoBack()?: false))
            }
        }

        onDispose {
            webView.destroy()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            webView.apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                settings.domStorageEnabled = true
                settings.javaScriptEnabled = true
                setWebContentsDebuggingEnabled(true)
                loadUrl(BuildConfig.BOTTLES_SIGNUP_URL)
                addJavascriptInterface(
                    SignupBridge { webEvent ->
                        when (webEvent) {
                            is SignupWebAction.OnToastOpen -> {  }
                            is SignupWebAction.OnWebViewClose -> onIntent(SignupIntent.ClickWebCloseButton)
                            is SignupWebAction.OnSignup -> onIntent(SignupIntent.ClickSignupButton(token = webEvent.token))
                        }
                    },
                    SignupBridge.NAME
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    BottlesTheme {
        SignupScreen(
            uiState = SignupUiState(),
            onIntent = { },
        )
    }
}