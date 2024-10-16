package com.team.bottles.feat.recommendation.detail

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class RecommendationDetailBridge(private val onAction: (RecommendationDetailWebAction) -> Unit) :
    RecommendationDetailBridgeListener {

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(RecommendationDetailWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(RecommendationDetailWebAction.OnToastMessage(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface RecommendationDetailWebAction {

    data object OnWebViewClose : RecommendationDetailWebAction

    data class OnToastMessage(val message: String) : RecommendationDetailWebAction

}

interface RecommendationDetailBridgeListener : BaseBridgeListener