package com.team.bottles.feat.bottle.arrivedbottles

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class ArrivedBottlesBridge(private val onAction: (ArrivedBottlesWebAction) -> Unit) : ArrivedBottlesBridgeListener {

    @JavascriptInterface
    override fun onBottleAccept() {
        onAction(ArrivedBottlesWebAction.OnBottleAccept)
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(ArrivedBottlesWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(ArrivedBottlesWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface ArrivedBottlesWebAction {

    data object OnBottleAccept : ArrivedBottlesWebAction

    data object OnWebViewClose : ArrivedBottlesWebAction

    data class OnToastOpen(val message: String) : ArrivedBottlesWebAction

}

interface ArrivedBottlesBridgeListener : BaseBridgeListener {

    fun onBottleAccept()

}
