package com.team.bottles.network.api

import com.team.bottles.network.dto.profile.reponse.ExistIntroductionResponse
import com.team.bottles.network.dto.profile.reponse.UserProfileResponse
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileService {

    @GET("/api/v1/profile")
    suspend fun getUserProfileInfo(): UserProfileResponse

    @POST("/api/v1/profile/introduction")
    suspend fun createIntroduction(
        @Body registerIntroductionRequest: RegisterIntroductionRequest
    )

    @Multipart
    @POST("/api/v1/profile/images")
    suspend fun postProfileImage(
        @Part file: MultipartBody.Part
    )

    @GET("/api/v1/profile/introduction/exist")
    suspend fun getIntroductionStatus(): ExistIntroductionResponse

}