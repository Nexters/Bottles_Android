package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toAuthResult
import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.repository.AuthRepository
import com.team.bottles.network.datasource.AuthDataSource
import com.team.bottles.network.dto.auth.request.FcmUpdateRequest
import com.team.bottles.network.dto.auth.request.KakaoSignInUpRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun kakaoLogin(accessToken: String): AuthResult =
        authDataSource.authenticateWithKakao(
            request = KakaoSignInUpRequest(
                code = accessToken,
                fcmDeviceToken = tokenDataSource.getFcmDeviceToken()
            )
        ).also { response ->
            tokenDataSource.setAccessToken(response.accessToken)
            tokenDataSource.setRefreshToken(response.refreshToken)
        }.toAuthResult()

    override suspend fun logout() {
        val accessToken = tokenDataSource.getAccessToken()
        authDataSource.logout(accessToken = accessToken)
        tokenDataSource.clearAccessTokenAndRefreshToken()
    }

    override suspend fun deleteUser() {
        val accessToken = tokenDataSource.getAccessToken()
        authDataSource.deleteUser(accessToken = accessToken)
        tokenDataSource.clearAccessTokenAndRefreshToken()
    }

    override suspend fun fetchLocalToken(): Token =
        Token(
            accessToken = tokenDataSource.getAccessToken(),
            refreshToken = tokenDataSource.getRefreshToken()
        )

    override suspend fun updateLocalToken(token: Token) {
        tokenDataSource.setAccessToken(accessToken = token.accessToken)
        tokenDataSource.setRefreshToken(refreshToken = token.refreshToken)
    }

    override suspend fun updateLocalFcmToken(fcmToken: String) {
        tokenDataSource.setFcmDeviceToken(fcmDeviceToken = fcmToken)
        tokenDataSource.setIsUpdatedFcmToken(isUpdated = true)
    }

    override suspend fun updateFcmTokenToServer() {
        val updatedFcmToken = tokenDataSource.getFcmDeviceToken()
        val accessToken = tokenDataSource.getAccessToken()
        val isUpdatedFcmToken = tokenDataSource.getIsUpdatedFcmToken()

        if (isUpdatedFcmToken && accessToken.isNotEmpty()) { // FCM 토큰이 갱신되어 있어야 하고, 로그인한 상태여야 한다.
            authDataSource.updateFcmToken(
                accessToken = accessToken,
                request = FcmUpdateRequest(
                    fcmToken = updatedFcmToken
                )
            )
            tokenDataSource.clearIsUpdatedFcmToken()
        }
    }

}
