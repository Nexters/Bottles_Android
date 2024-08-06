package com.team.bottles.network.datasource

import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import java.io.File

interface ProfileDataSource {

    suspend fun createIntroduction(request: RegisterIntroductionRequest)

    suspend fun postProfileImage(imageFile: File)

}