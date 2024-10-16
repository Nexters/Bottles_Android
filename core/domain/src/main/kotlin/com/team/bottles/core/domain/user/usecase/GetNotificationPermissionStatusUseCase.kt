package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class GetNotificationPermissionStatusUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): GetNotificationPermissionStatusUseCase {

    override suspend fun invoke(): Boolean =
        userRepository.getNotificationPermissionStatus()

}

interface GetNotificationPermissionStatusUseCase {

    suspend operator fun invoke(): Boolean

}