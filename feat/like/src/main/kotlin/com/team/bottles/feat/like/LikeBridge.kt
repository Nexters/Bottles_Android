package com.team.bottles.feat.like

import android.webkit.JavascriptInterface

internal class LikeBridge(private val onAction: (LikeWebAction) -> Unit) : LikeBridgeListener {

    @JavascriptInterface
    override fun openLink(href: String) {
        onAction(LikeWebAction.OnClickBottle(href = href))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface LikeWebAction {

    data class OnClickBottle(val href: String) : LikeWebAction

}

interface LikeBridgeListener {

    fun openLink(href: String)

}
