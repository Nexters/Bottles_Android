package com.team.bottles.feat.profile.createprofile

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener
import com.team.bottles.core.domain.auth.model.Token
import kotlinx.serialization.json.Json

internal class CreateProfileBridge(private val onAction: (CreateProfileWebAction) -> Unit) :
    CreateProfileBridgeListener {

    @JavascriptInterface
    override fun onCreateProfileComplete(json: String) {
        val token = Json.decodeFromString<Token>(json)
        onAction(CreateProfileWebAction.OnCreateComplete(token =token))
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

    data class OnCreateComplete(val token: Token) : CreateProfileWebAction

    data object OnWebViewClose : CreateProfileWebAction

    data class OnToastOpen(val message: String) : CreateProfileWebAction

}

interface CreateProfileBridgeListener : BaseBridgeListener {

    fun onCreateProfileComplete(json: String)

}
