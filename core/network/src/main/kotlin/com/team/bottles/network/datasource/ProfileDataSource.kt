package com.team.bottles.network.datasource

import com.team.bottles.network.dto.profile.reponse.ExistIntroductionResponse
import com.team.bottles.network.dto.profile.reponse.UserProfileResponse
import com.team.bottles.network.dto.profile.reponse.UserProfileStatusResponse
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import java.io.File

interface ProfileDataSource {

    suspend fun createIntroduction(request: RegisterIntroductionRequest)

    suspend fun postProfileImage(imageFile: File)

    suspend fun fetchUserProfile(): UserProfileResponse

    suspend fun fetchIntroductionStatus(): ExistIntroductionResponse

    suspend fun fetchUserProfileStatus(): UserProfileStatusResponse

}
