package com.team.bottles.network.api

import com.team.bottles.network.dto.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.response.KakaoSignInUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/auth/kakao")
    suspend fun loginWithKakao(
        @Body kakaoSignInUpRequest: KakaoSignInUpRequest
    ): KakaoSignInUpResponse

}