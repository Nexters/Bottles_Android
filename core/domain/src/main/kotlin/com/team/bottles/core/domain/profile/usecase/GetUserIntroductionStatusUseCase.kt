package com.team.bottles.core.domain.profile.usecase

import com.team.bottles.core.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetUserIntroductionStatusUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): GetUserIntroductionStatusUseCase {

    override suspend fun invoke(): Boolean =
        profileRepository.loadUserIntroductionStatus()

}

interface GetUserIntroductionStatusUseCase {

    suspend operator fun invoke(): Boolean

}