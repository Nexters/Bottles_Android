package com.team.bottles.core.domain.auth.repository

import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.core.domain.auth.model.Token

interface AuthRepository {

    suspend fun kakaoLogin(accessToken: String): AuthResult

    suspend fun logout()

    suspend fun deleteUser()

    suspend fun fetchLocalToken(): Token

    suspend fun updateLocalToken(token: Token)

    suspend fun updateLocalFcmToken(fcmToken: String)

    suspend fun updateFcmTokenToServer()

    suspend fun getSavedLocalFcmToken(): String

    suspend fun getLatestAppVersion(): Int

    suspend fun getRequiredAppVersion(): Int

}