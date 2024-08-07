package com.team.bottles.core.domain.profile.usecase

import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetUserProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : GetUserProfileUseCase {

    override suspend fun invoke(): UserProfile =
        profileRepository.loadUserProfile()

}

interface GetUserProfileUseCase {

    suspend operator fun invoke(): UserProfile

}