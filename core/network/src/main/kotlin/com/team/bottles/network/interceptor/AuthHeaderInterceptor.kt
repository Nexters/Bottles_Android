package com.team.bottles.network.interceptor

import com.team.bottles.core.datastore.datasource.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthHeaderInterceptor @Inject constructor(
    private val tokenDataSource: TokenDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { tokenDataSource.getAccessToken() }

        return chain.proceed(
            chain.request().newBuilder()
                .addHeader(HEADER_NAME, "$TOKEN_TYPE $accessToken")
                .build(),
        )
    }

    companion object {
        private const val HEADER_NAME = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }

}
