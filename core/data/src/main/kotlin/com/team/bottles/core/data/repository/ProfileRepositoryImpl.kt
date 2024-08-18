package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toRequest
import com.team.bottles.core.data.mapper.toUserProfile
import com.team.bottles.core.data.mapper.toUserProfileStatus
import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.domain.profile.model.UserProfileStatus
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import com.team.bottles.network.datasource.ProfileDataSource
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun createIntroduction(questionsAndAnswers: List<QuestionAndAnswer>) {
        profileDataSource.createIntroduction(request = questionsAndAnswers.toRequest())
    }

    override suspend fun uploadProfileImage(imageFile: File) {
        profileDataSource.postProfileImage(imageFile = imageFile)
    }

    override suspend fun loadUserProfile(): UserProfile =
        profileDataSource.fetchUserProfile().toUserProfile()

    override suspend fun loadUserIntroductionStatus(): Boolean =
        profileDataSource.fetchIntroductionStatus().isExist

    override suspend fun loadUserProfileStatus(): UserProfileStatus =
        profileDataSource.fetchUserProfileStatus().toUserProfileStatus()

}