package com.team.bottles.core.domain.profile.usecase

import com.team.bottles.core.domain.profile.model.UserProfileStatus
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetUserProfileStatusUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
): GetUserProfileStatusUseCase {

    override suspend fun invoke(): UserProfileStatus =
        profileRepository.loadUserProfileStatus()

}

interface GetUserProfileStatusUseCase {

    suspend operator fun invoke(): UserProfileStatus

}