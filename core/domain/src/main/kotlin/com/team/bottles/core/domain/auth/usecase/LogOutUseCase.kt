package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): LogOutUseCase {

    override suspend fun invoke() {
        authRepository.logout()
    }

}

interface LogOutUseCase {

    suspend operator fun invoke()

}