package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class UpdateSettingNotificationUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): UpdateSettingNotificationUseCase {

    override suspend fun invoke(notification: Notification) {
        userRepository.updateSettingNotification(notification = notification)
    }

}

interface UpdateSettingNotificationUseCase {

    suspend operator fun invoke(notification: Notification)

}