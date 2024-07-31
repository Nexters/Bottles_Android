package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LoginWithKakaoUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoginWithKakaoUseCase {

    override suspend operator fun invoke(accessToken: String): AuthResult =
        authRepository.kakaoLogin(accessToken = accessToken)

}

interface LoginWithKakaoUseCase {

    suspend operator fun invoke(accessToken: String): AuthResult

}