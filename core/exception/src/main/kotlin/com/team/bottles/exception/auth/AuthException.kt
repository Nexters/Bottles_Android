package com.team.bottles.exception.auth

import kotlinx.coroutines.flow.MutableSharedFlow

object BottlesExceptionBus {

    val authEvents = MutableSharedFlow<AuthEvent>(replay = 1)

}

sealed class AuthException {

    data object Logout : AuthEvent()

}