package com.team.bottles.core.data.datasource.remote

import com.team.bottles.network.api.AuthService
import com.team.bottles.network.dto.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.response.KakaoSignInUpResponse
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {

    override suspend fun authenticateWithKakao(request: KakaoSignInUpRequest): KakaoSignInUpResponse =
        authService.loginWithKakao(kakaoSignInUpRequest = request)

}