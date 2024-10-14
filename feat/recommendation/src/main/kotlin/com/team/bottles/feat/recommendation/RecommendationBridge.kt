package com.team.bottles.feat.recommendation

import android.webkit.JavascriptInterface

internal class RecommendationBridge(private val onAction: (RecommendationWebAction) -> Unit) : RecommendationBridgeListener {

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(RecommendationWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun openLink(href: String) {
        onAction(RecommendationWebAction.OnClickBottle(href = href))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface RecommendationWebAction {

    data object OnWebViewClose : RecommendationWebAction

    data class OnClickBottle(val href: String) : RecommendationWebAction

}

interface RecommendationBridgeListener {

    fun openLink(href: String)

    fun onWebViewClose()

}
