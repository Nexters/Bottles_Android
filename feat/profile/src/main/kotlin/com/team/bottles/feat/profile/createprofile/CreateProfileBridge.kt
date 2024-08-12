package com.team.bottles.feat.profile.createprofile

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class CreateProfileBridge(private val onAction: (CreateProfileWebAction) -> Unit) :
    CreateProfileBridgeListener {

    @JavascriptInterface
    override fun onCreateProfileComplete() {
        onAction(CreateProfileWebAction.OnCreateComplete)
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(CreateProfileWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(CreateProfileWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface CreateProfileWebAction {

    data object OnCreateComplete : CreateProfileWebAction

    data object OnWebViewClose : CreateProfileWebAction

    data class OnToastOpen(val message: String) : CreateProfileWebAction

}

interface CreateProfileBridgeListener : BaseBridgeListener {

    fun onCreateProfileComplete()

}
