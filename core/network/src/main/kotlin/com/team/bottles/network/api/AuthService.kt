package com.team.bottles.network.api

import com.team.bottles.network.dto.auth.request.AuthSmsRequest
import com.team.bottles.network.dto.auth.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.auth.request.ReissueTokenRequest
import com.team.bottles.network.dto.auth.request.SignUpRequest
import com.team.bottles.network.dto.auth.request.SmsSignInRequest
import com.team.bottles.network.dto.auth.response.KakaoSignInUpResponse
import com.team.bottles.network.dto.auth.response.TokensResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/auth/kakao")
    suspend fun loginWithKakao(
        @Body kakaoSignInUpRequest: KakaoSignInUpRequest
    ): KakaoSignInUpResponse

    @POST("/api/v1/auth/refresh")
    suspend fun refresh(
        @Header("Authorization") refreshToken: String,
        @Body reissueTokenRequest: ReissueTokenRequest,
    ): TokensResponse

    @POST("/api/v1/auth/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String
    )

    @POST("/api/v1/auth/delete")
    suspend fun deleteUser(
        @Header("Authorization") accessToken: String
    )

    @POST("/api/v1/auth/signup")
    suspend fun signup(
        @Body singUpRequest: SignUpRequest
    ): TokensResponse

    @POST("/api/v1/auth/sms/login")
    suspend fun loginWithSms(
        @Body smsSignInRequest: SmsSignInRequest
    ): TokensResponse

    @POST("/api/v1/auth/sms/send")
    suspend fun postSms(
        @Body authSmsRequest: AuthSmsRequest
    )

    @POST("/api/v1/auth/sms/send/check")
    suspend fun checkSendSms(
        @Body authSmsRequest: AuthSmsRequest
    )

}
