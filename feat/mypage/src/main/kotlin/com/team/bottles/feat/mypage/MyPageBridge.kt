package com.team.bottles.feat.mypage

import android.webkit.JavascriptInterface

internal class MyPageBridge(private val onAction: (MyPageWebAction) -> Unit) : MyPageBridgeListener {

    @JavascriptInterface
    override fun logout() {
        onAction(MyPageWebAction.OnLogOut)
    }

    @JavascriptInterface
    override fun deleteUser() {
        onAction(MyPageWebAction.OnDeleteUser)
    }

    companion object {
        const val NAME = "Native"
    }

}

sealed interface MyPageWebAction {

    data object OnLogOut : MyPageWebAction

    data object OnDeleteUser : MyPageWebAction

}

interface MyPageBridgeListener {

    fun logout()

    fun deleteUser()

}
