package com.team.bottles.feat.signup

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener
import com.team.bottles.core.domain.auth.model.Token
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

internal class SignupBridge(private val onAction: (SignupWebAction) -> Unit) :
    SignupBridgeListener {

    @JavascriptInterface
    override fun onSignup(json: String) {
        val token = Json.decodeFromString<Token>(json)
        onAction(SignupWebAction.OnSignup(token =token))
    }

    @JavascriptInterface
    override fun openLink(href: String) {
        val link = Json.decodeFromString<SignupLink>(href)
        onAction(SignupWebAction.OnOpenLink(href = link.href))
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(SignupWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(SignupWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

@Serializable
private data class SignupLink(
    val href: String
)

sealed interface SignupWebAction {

    data class OnSignup(val token: Token) : SignupWebAction

    data class OnOpenLink(val href: String) : SignupWebAction

    data object OnWebViewClose : SignupWebAction

    data class OnToastOpen(val message: String) : SignupWebAction

}

interface SignupBridgeListener : BaseBridgeListener {

    fun onSignup(json: String)

    fun openLink(href: String)

}
