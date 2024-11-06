package com.team.bottles.feat.profile.introduction

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.team.bottles.feat.profile.edit.EditProfileBridge
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent
import com.team.bottles.feat.profile.introduction.mvi.IntroductionUiState

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun IntroductionScreen(
    uiState: IntroductionUiState,
    onIntent: (IntroductionIntent) -> Unit
) {
    var canGoBack by remember { mutableStateOf(false) }
    var mFilePathCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            mFilePathCallback?.onReceiveValue(arrayOf(uri))
        } else {
            mFilePathCallback?.onReceiveValue(null)
        }
        mFilePathCallback = null
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        if (uris.isNotEmpty()) {
            mFilePathCallback?.onReceiveValue(uris.toTypedArray())
        } else {
            mFilePathCallback?.onReceiveValue(null)
        }
        mFilePathCallback = null
    }

    if (uiState.token.accessToken.isNotEmpty() && uiState.token.refreshToken.isNotEmpty()) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding(),
            factory = { context ->
                WebView(context).apply {
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

                    webChromeClient = object : WebChromeClient() {
                        override fun onShowFileChooser(
                            webView: WebView?,
                            filePathCallback: ValueCallback<Array<Uri>>?,
                            fileChooserParams: FileChooserParams?
                        ): Boolean {
                            mFilePathCallback = filePathCallback

                            val isMultipleSelect =
                                fileChooserParams?.mode == FileChooserParams.MODE_OPEN_MULTIPLE

                            if (isMultipleSelect) {
                                multiplePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            } else {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }

                            return true
                        }
                    }

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

                    addJavascriptInterface(
                        IntroductionBridge { webAction ->
                            when (webAction) {
                                is IntroductionWebAction.OnIntroductionComplete -> onIntent(
                                    IntroductionIntent.ClickWebCompleteButton
                                )

                                is IntroductionWebAction.OnWebViewClose -> onIntent(
                                    IntroductionIntent.ClickWebCloseButton
                                )

                                is IntroductionWebAction.OnToastOpen -> onIntent(
                                    IntroductionIntent.ShowToastMessage(message = webAction.message)
                                )
                            }
                        },
                        EditProfileBridge.NAME
                    )
                }
            },
            update = { webView ->
                webView.loadUrl(uiState.url)
            }
        )
    }
}
