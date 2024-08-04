package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class WebViewConnectUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): WebViewConnectUseCase {

    override suspend fun getLocalToken(): Token =
        authRepository.fetchLocalToken()

    override suspend fun setLocalToken(token: Token) {
        authRepository.updateLocalToken(token = token)
    }

}


interface WebViewConnectUseCase {

    suspend fun getLocalToken(): Token

    suspend fun setLocalToken(token: Token)

}