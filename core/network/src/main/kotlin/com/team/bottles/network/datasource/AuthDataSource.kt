package com.team.bottles.network.datasource

import com.team.bottles.network.dto.auth.request.AuthSmsRequest
import com.team.bottles.network.dto.auth.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.auth.request.SignUpRequest
import com.team.bottles.network.dto.auth.request.SmsSignInRequest
import com.team.bottles.network.dto.auth.response.KakaoSignInUpResponse
import com.team.bottles.network.dto.auth.response.TokensResponse

interface AuthDataSource {

    suspend fun authenticateWithKakao(
        request: KakaoSignInUpRequest
    ): KakaoSignInUpResponse

    suspend fun refreshAccessToken(
        refreshToken: String
    ): Result<TokensResponse>

    suspend fun signup(
        request: SignUpRequest
    ): TokensResponse

    suspend fun loginWithSms(
        request: SmsSignInRequest
    ): TokensResponse

    suspend fun logout(accessToken: String)

    suspend fun deleteUser(accessToken: String)

    suspend fun getSmsCode(request: AuthSmsRequest)

    suspend fun checkSendSms(request: AuthSmsRequest)

}