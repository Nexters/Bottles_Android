package com.team.bottles.feat.like

import android.util.Log
import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class LikeBridge(private val onAction: (LikeWebAction) -> Unit) : LikeBridgeListener {

    @JavascriptInterface
    override fun openLink(href: String) {
        Log.d("하이퍼 링크", href)
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
