package com.team.bottles.feat.signup

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.feat.signup.mvi.SignupIntent
import com.team.bottles.feat.signup.mvi.SignupUiState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onIntent: (SignupIntent) -> Unit,
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                SignupBridge { webAction ->
                    when (webAction) {
                        is SignupWebAction.OnToastOpen -> Toast.makeText(context, webAction.message, Toast.LENGTH_SHORT).show()
                        is SignupWebAction.OnWebViewClose -> onIntent(SignupIntent.ClickWebCloseButton)
                        is SignupWebAction.OnSignup -> onIntent(SignupIntent.ClickWebSignupButton(token = webAction.token))
                        is SignupWebAction.OnOpenLink -> onIntent(SignupIntent.ClickWebLink(href = webAction.href))
                    }
                },
                SignupBridge.NAME
            )
        }
    }

    BottlesWebView(
        url = BuildConfig.BOTTLES_SIGNUP_URL,
        webView = webView
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    BottlesTheme {
        SignupScreen(
            uiState = SignupUiState,
            onIntent = { },
        )
    }
}