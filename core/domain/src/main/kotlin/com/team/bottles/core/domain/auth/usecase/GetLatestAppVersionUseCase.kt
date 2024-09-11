package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class GetLatestAppVersionUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
): GetLatestAppVersionUseCase {

    override suspend fun invoke(): Int =
        authRepository.getLatestAppVersion()

}

interface GetLatestAppVersionUseCase {

    suspend operator fun invoke(): Int

}