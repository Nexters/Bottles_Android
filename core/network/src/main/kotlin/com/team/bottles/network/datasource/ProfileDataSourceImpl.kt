package com.team.bottles.network.datasource

import com.team.bottles.network.api.ProfileService
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileService: ProfileService,
) : ProfileDataSource {

    override suspend fun createIntroduction(request: RegisterIntroductionRequest) {
        profileService.createIntroduction(registerIntroductionRequest = request)
    }

    override suspend fun postProfileImage(imageFile: File) {
        profileService.postProfileImage(
            file = MultipartBody.Part.createFormData(
                name = "file",
                filename = imageFile.name,
                body = imageFile.asRequestBody("image/*".toMediaType())
            )
        )
    }

}