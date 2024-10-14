package com.team.bottles.core.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
@Composable
fun BottlesWebView(
    modifier: Modifier = Modifier,
    url: String,
    webView: WebView,
) {
    var canGoBack by remember { mutableStateOf(false) }

    BackHandler(enabled = canGoBack) {
        if (webView.canGoBack()) {
            webView.goBack()
        }
    }

    DisposableEffect(webView) {
        webView.apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

            settings.run {
                domStorageEnabled = true
                javaScriptEnabled = true
                defaultTextEncodingName = "UTF-8"
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setSupportZoom(false)
                builtInZoomControls = false
            }

            setWebContentsDebuggingEnabled(true)

            resumeTimers()

            loadUrl(url)

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    canGoBack = view?.canGoBack() == true
                }

                override fun doUpdateVisitedHistory(
                    view: WebView?,
                    url: String?,
                    isReload: Boolean
                ) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                    canGoBack = view?.canGoBack() == true
                }
            }
        }

        onDispose {
            webView.destroy()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { webView }
    )
}