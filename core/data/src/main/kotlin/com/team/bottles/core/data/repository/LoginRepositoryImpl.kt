package com.team.bottles.core.data.repository

import com.team.bottles.core.data.datasource.remote.AuthDataSource
import com.team.bottles.core.data.model.toAuthResult
import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.core.domain.auth.repository.AuthRepository
import com.team.bottles.network.dto.request.KakaoSignInUpRequest
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun kakaoLogin(accessToken: String): AuthResult =
        authDataSource.authenticateWithKakao(
            request = KakaoSignInUpRequest(code = accessToken)
        ).also { response ->
            tokenDataSource.setAccessToken(response.accessToken)
        }.toAuthResult()

}
