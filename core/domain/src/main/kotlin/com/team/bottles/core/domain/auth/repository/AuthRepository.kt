package com.team.bottles.core.domain.auth.repository

import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.core.domain.auth.model.Token

interface AuthRepository {

    suspend fun kakaoLogin(accessToken: String): AuthResult

    suspend fun logout()

    suspend fun fetchLocalToken(): Token

    suspend fun updateLocalToken(token: Token)

}