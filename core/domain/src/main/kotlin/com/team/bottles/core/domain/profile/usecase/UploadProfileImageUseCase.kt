package com.team.bottles.core.domain.profile.usecase

import com.team.bottles.core.domain.profile.repository.ProfileRepository
import java.io.File
import javax.inject.Inject

class UploadProfileImageUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : UploadProfileImageUseCase{

    override suspend fun invoke(imageFile: File) {
        profileRepository.uploadProfileImage(imageFile = imageFile)
    }

}

interface UploadProfileImageUseCase {

    suspend operator fun invoke(imageFile: File)

}