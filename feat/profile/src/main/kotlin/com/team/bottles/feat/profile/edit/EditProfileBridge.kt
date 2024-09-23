package com.team.bottles.feat.profile.edit

import android.webkit.JavascriptInterface

internal class EditProfileBridge(private val onAction: (EditProfileWebAction) -> Unit) :
    EditProfileBridgeListener {

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(EditProfileWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun openWebView(url: String) {
        // TODO : 웹 메서드 생성시 구현
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface EditProfileWebAction {

    data object OnWebViewClose : EditProfileWebAction

}

interface EditProfileBridgeListener {

    fun onWebViewClose()

    fun openWebView(url: String)

}
