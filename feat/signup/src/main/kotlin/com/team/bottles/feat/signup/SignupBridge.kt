package com.team.bottles.feat.signup

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener
import com.team.bottles.core.domain.auth.model.Token
import kotlinx.serialization.json.Json

internal class SignupBridge(private val onEvent: (SignupWebEvent) -> Unit) :
    SignupBridgeListener() {

    @JavascriptInterface
    override fun onSignup(json: String) {
        val token = Json.decodeFromString<Token>(json)
        onEvent(
            SignupWebEvent.OnSignup(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
        )
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onEvent(SignupWebEvent.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onEvent(SignupWebEvent.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface SignupWebEvent {

    data class OnSignup(val accessToken: String, val refreshToken: String) : SignupWebEvent

    data object OnWebViewClose : SignupWebEvent

    data class OnToastOpen(val message: String) : SignupWebEvent

}

abstract class SignupBridgeListener : BaseBridgeListener() {

    abstract fun onSignup(json: String)

}
