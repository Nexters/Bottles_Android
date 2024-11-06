package com.team.bottles.feat.profile.introduction

import android.webkit.JavascriptInterface
import com.team.bottles.core.common.base.BaseBridgeListener

internal class IntroductionBridge(private val onAction: (IntroductionWebAction) -> Unit) : IntroductionWebListener {

    @JavascriptInterface
    override fun onIntroductionComplete() {
        onAction(IntroductionWebAction.OnIntroductionComplete)
    }

    @JavascriptInterface
    override fun onWebViewClose() {
        onAction(IntroductionWebAction.OnWebViewClose)
    }

    @JavascriptInterface
    override fun onToastOpen(message: String) {
        onAction(IntroductionWebAction.OnToastOpen(message = message))
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface IntroductionWebAction {

    data object OnIntroductionComplete : IntroductionWebAction

    data object OnWebViewClose : IntroductionWebAction

    data class OnToastOpen(val message: String) : IntroductionWebAction

}

sealed interface IntroductionWebListener : BaseBridgeListener{

    fun onIntroductionComplete()

}