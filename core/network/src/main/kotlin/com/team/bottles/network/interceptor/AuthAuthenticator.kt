package com.team.bottles.network.interceptor

import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.exception.BottlesException
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

    private suspend fun getNewAccessToken(): String {
        val currentRefreshToken = tokenDataSource.getRefreshToken()
        val fcmDeviceToken = tokenDataSource.getFcmDeviceToken()

        authDataSource.refreshAccessToken( // 토큰 리프레쉬 API 호출
            refreshToken = currentRefreshToken,
            request = ReissueTokenRequest(
                fcmDeviceToken = fcmDeviceToken
            )
        ).fold(
            onSuccess = { response -> // 리프레쉬 성공 시 -> 토큰 갈아 끼우기
                tokenDataSource.setAccessToken(accessToken = response.accessToken)
                tokenDataSource.setRefreshToken(refreshToken = response.refreshToken)

                return response.accessToken
            },
            onFailure = { e -> // 리프레쉬 실패 시 -> 리프레쉬 토큰이 유효기간이 지났거나 알수 없는 오류가 발생했다는 뜻
                val accessToken = tokenDataSource.getAccessToken()

                authDataSource.logout(accessToken = accessToken) // 서버에 로그아웃 요청을 보내기 위한 방법 생각하기
                tokenDataSource.clearAccessTokenAndRefreshToken()

                throw BottlesException(code = 100, message = e.message) // 로그인 화면으로 이동하라는 커스텀 예외 추가
            }
        )
    }

    companion object {
        private const val HEADER_NAME = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }

}