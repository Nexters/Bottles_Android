package com.team.bottles.network.datasource

import com.team.bottles.network.api.AuthService
import com.team.bottles.network.dto.auth.request.AuthSmsRequest
import com.team.bottles.network.dto.auth.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.auth.request.SignUpRequest
import com.team.bottles.network.dto.auth.request.SmsSignInRequest
import com.team.bottles.network.dto.auth.response.KakaoSignInUpResponse
import com.team.bottles.network.dto.auth.response.TokensResponse
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {

    override suspend fun authenticateWithKakao(request: KakaoSignInUpRequest): KakaoSignInUpResponse =
        authService.loginWithKakao(kakaoSignInUpRequest = request)

    override suspend fun refreshAccessToken(refreshToken: String): Result<TokensResponse> =
        runCatching { authService.refresh(refreshToken = refreshToken) }

    override suspend fun signup(request: SignUpRequest): TokensResponse =
        authService.signup(singUpRequest = request)

    override suspend fun loginWithSms(request: SmsSignInRequest): TokensResponse =
        authService.loginWithSms(smsSignInRequest = request)

    override suspend fun logout(accessToken: String) {
        authService.logout(accessToken = accessToken)
    }

    override suspend fun deleteUser(accessToken: String) {
        authService.deleteUser(accessToken = accessToken)
    }

    override suspend fun getSmsCode(request: AuthSmsRequest) {
        authService.postSms(authSmsRequest = request)
    }

    override suspend fun checkSendSms(request: AuthSmsRequest) {
        authService.checkSendSms(authSmsRequest = request)
    }

}