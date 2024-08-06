package com.team.bottles.network.api

import com.team.bottles.network.dto.profile.reponse.UserProfileResponse
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileService {

    @GET("/api/v1/profile")
    suspend fun getMyProfileInfo(): UserProfileResponse

    @POST("/api/v1/profile/introduction")
    suspend fun createIntroduction(
        @Body registerIntroductionRequest: RegisterIntroductionRequest
    )

}