package com.team.bottles.network.interceptor

import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.network.datasource.AuthDataSource
import com.team.bottles.network.dto.auth.request.ReissueTokenRequest
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val authDataSource: AuthDataSource,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val newAccessToken = runBlocking { getNewAccessToken() }

        return response.request.newBuilder()
            .header(HEADER_NAME, "$TOKEN_TYPE $newAccessToken")
            .build()
    }

    private suspend fun getNewAccessToken(): String? {
        val currentRefreshToken = tokenDataSource.getRefreshToken()
        val fcmDeviceToken = tokenDataSource.getFcmDeviceToken()

        val response = authDataSource.refreshAccessToken(
            refreshToken = currentRefreshToken,
            request = ReissueTokenRequest(
                fcmDeviceToken = fcmDeviceToken
            )
        ).getOrNull()

        if (response != null) {
            tokenDataSource.setAccessToken(accessToken = response.accessToken)
            tokenDataSource.setRefreshToken(refreshToken = response.refreshToken)

            return response.accessToken
        } else {
            val accessToken = tokenDataSource.getAccessToken()
            authDataSource.logout(accessToken = accessToken)
            tokenDataSource.clearAccessTokenAndRefreshToken()
            return null
        }
    }

    companion object {
        private const val HEADER_NAME = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }

}
