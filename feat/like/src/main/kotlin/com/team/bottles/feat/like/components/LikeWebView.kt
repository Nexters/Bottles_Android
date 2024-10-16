package com.team.bottles.feat.like.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.team.bottles.core.designsystem.theme.LocalLikeTabWebViewComposition
import com.team.bottles.feat.like.LikeBridge
import com.team.bottles.feat.like.LikeWebAction

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun LikeWebView(
    modifier: Modifier = Modifier,
    url: String,
    onClickBottle: (href: String) -> Unit
) {
    val webView = LocalLikeTabWebViewComposition.current
    var savedUrl by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        if (url != savedUrl) {
            webView?.loadUrl(url)
        }
    }

    DisposableEffect(Unit) {
        webView?.apply {
            addJavascriptInterface(
                LikeBridge { webAction ->
                    when (webAction) {
                        is LikeWebAction.OnClickBottle -> onClickBottle(webAction.href)
                    }
                },
                LikeBridge.NAME
            )

            setWebContentsDebuggingEnabled(true)

            resumeTimers()

            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    savedUrl = originalUrl
                }
            }

            settings.run {
                domStorageEnabled = true
                javaScriptEnabled = true
                defaultTextEncodingName = "UTF-8"
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setSupportZoom(false)
                builtInZoomControls = false
            }
        }

        onDispose {
            webView?.pauseTimers()
            webView?.clearFocus()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            webView ?: WebView(context)
        }
    )
}