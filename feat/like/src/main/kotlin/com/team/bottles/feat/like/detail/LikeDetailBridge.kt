package com.team.bottles.feat.like.detail

import android.webkit.JavascriptInterface

internal class LikeDetailBridge(private val onAction: (LikeWebAction) -> Unit) : LikeBridgeListener {

    @JavascriptInterface
    override fun onBottleAccept() {
        onAction(LikeWebAction.OnBottleAccept)
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(LikeWebAction.OnWebViewClose)
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface LikeWebAction {

    data object OnBottleAccept : LikeWebAction

    data object OnWebViewClose : LikeWebAction

}

interface LikeBridgeListener {

    fun onBottleAccept()

    fun onWebViewClose()

}
