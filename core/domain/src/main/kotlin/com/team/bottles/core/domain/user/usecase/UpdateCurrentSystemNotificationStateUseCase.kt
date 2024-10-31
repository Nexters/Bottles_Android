package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class UpdateCurrentSystemNotificationStateUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
): UpdateCurrentSystemNotificationStateUseCase {

    override suspend fun invoke() {
        userRepository.updateCurrentSystemNotificationState()
    }

}

interface UpdateCurrentSystemNotificationStateUseCase  {

    suspend operator fun invoke()

}