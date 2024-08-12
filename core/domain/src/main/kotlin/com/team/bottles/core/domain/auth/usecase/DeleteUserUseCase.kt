package com.team.bottles.core.domain.auth.usecase

import com.team.bottles.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): DeleteUserUseCase {

    override suspend fun invoke() {
        authRepository.deleteUser()
    }

}

interface DeleteUserUseCase {

    suspend operator fun invoke()

}