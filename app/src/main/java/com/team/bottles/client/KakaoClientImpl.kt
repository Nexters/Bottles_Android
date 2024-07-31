package com.team.bottles.client

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.team.bottles.core.domain.login.ThirdPartyAccessToken
import com.team.bottles.core.domain.login.kakao.KakaoClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoClientImpl @Inject constructor(
    @ActivityContext private val activityContext: Context,
) : KakaoClient {

    override suspend fun login(): ThirdPartyAccessToken {
        val isKakaoTalkInstalled = UserApiClient.instance.isKakaoTalkLoginAvailable(activityContext)
        return if (isKakaoTalkInstalled) {
            loginWithKakaoTalk()
        } else {
            loginWithWebView()
        }.toThirdPartyAccessToken()
    }

    private suspend fun loginWithKakaoTalk(): OAuthToken {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoTalk(activityContext) { token: OAuthToken?, error: Throwable? ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        continuation.cancel()
                    }

                    UserApiClient.instance.loginWithKakaoAccount(activityContext) { accountToken: OAuthToken?, accountError: Throwable? ->
                        when {
                            accountError != null -> {
                                continuation.resumeWithException(accountError)
                            }

                            accountToken != null -> {
                                continuation.resume(accountToken)
                            }
                        }
                    }
                } else if (token != null) {
                    continuation.resume(token)
                }
            }
        }
    }

    private suspend fun loginWithWebView(): OAuthToken {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoAccount(activityContext) { token: OAuthToken?, error: Throwable? ->
                when {
                    error != null -> {
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.cancel()
                        } else {
                            continuation.resumeWithException(error)
                        }
                    }

                    token != null -> {
                        continuation.resume(token)
                    }
                }
            }
        }
    }
}

private fun OAuthToken.toThirdPartyAccessToken() = ThirdPartyAccessToken(accessToken = accessToken)