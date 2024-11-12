package com.team.bottles.feat.profile.edit

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class EditProfileBridge(private val onAction: (EditProfileWebAction) -> Unit) :
    EditProfileBridgeListener {

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(EditProfileWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(EditProfileWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface EditProfileWebAction {

    data object OnWebViewClose : EditProfileWebAction

    data class OnToastOpen(val message: String) : EditProfileWebAction

}

interface EditProfileBridgeListener : BaseBridgeListener
