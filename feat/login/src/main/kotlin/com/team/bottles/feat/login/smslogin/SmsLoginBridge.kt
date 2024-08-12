package com.team.bottles.feat.login.smslogin

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener
import com.team.bottles.core.domain.auth.model.Token
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

internal class SmsLoginBridge(private val onAction: (SmsLoginWebAction) -> Unit) : SmsLoginBridgeListener {

    @JavascriptInterface
    override fun onLogin(json: String) {
        val result = Json.decodeFromString<SmsLoginWebResult>(json)
        onAction(SmsLoginWebAction.OnLogin(smsLoginResult = result))
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(SmsLoginWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(SmsLoginWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

@Serializable
data class SmsLoginWebResult(
    val token: Token,
    val hasCompleteIntroduction: Boolean
)

sealed interface SmsLoginWebAction {

    data class OnLogin(val smsLoginResult: SmsLoginWebResult) : SmsLoginWebAction

    data object OnWebViewClose : SmsLoginWebAction

    data class OnToastOpen(val message: String) : SmsLoginWebAction

}

interface SmsLoginBridgeListener : BaseBridgeListener {

    fun onLogin(json: String)

}