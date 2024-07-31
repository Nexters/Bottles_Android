package com.team.bottles.core.domain.auth.repository

import com.team.bottles.core.domain.auth.model.AuthResult

interface AuthRepository {

    suspend fun kakaoLogin(accessToken: String): AuthResult

}