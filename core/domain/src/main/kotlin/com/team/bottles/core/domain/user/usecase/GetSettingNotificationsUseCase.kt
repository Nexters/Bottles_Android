package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class GetSettingNotificationsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : GetSettingNotificationsUseCase {

    override suspend fun invoke(): List<Notification> =
        userRepository.loadSettingNotifications()

}

interface GetSettingNotificationsUseCase {

    suspend operator fun invoke(): List<Notification>

}