package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class GetRequiredAppVersionUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : GetRequiredAppVersionUseCase {

    override suspend fun invoke(): String =
        authRepository.getRequiredAppVersion()

}

interface GetRequiredAppVersionUseCase {

    suspend operator fun invoke(): String

}