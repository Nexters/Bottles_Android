package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class UpdateActivateMatchingUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): UpdateActivateMatchingUseCase {

    override suspend fun invoke(isActivate: Boolean) {
        userRepository.updateActivateMatching(isActivate = isActivate)
    }

}

interface UpdateActivateMatchingUseCase {

    suspend operator fun invoke(isActivate: Boolean)

}