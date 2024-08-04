package com.team.bottles.core.common.base

abstract class BaseBridgeListener {

    abstract fun onWebViewClose()

    open fun onToastOpen(message: String) {}

}