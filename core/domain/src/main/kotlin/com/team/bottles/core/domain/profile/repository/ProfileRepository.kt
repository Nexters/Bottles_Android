package com.team.bottles.core.domain.profile.repository

import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.domain.profile.model.UserProfileStatus
import java.io.File

interface ProfileRepository {

    suspend fun createIntroduction(questionsAndAnswers: List<QuestionAndAnswer>)

    suspend fun uploadProfileImage(imageFile: File)

    suspend fun loadUserProfile(): UserProfile

    suspend fun loadUserIntroductionStatus(): Boolean

    suspend fun loadUserProfileStatus(): UserProfileStatus

}