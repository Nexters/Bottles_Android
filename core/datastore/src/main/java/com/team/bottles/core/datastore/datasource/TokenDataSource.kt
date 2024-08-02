package com.team.bottles.core.datastore.datasource

interface TokenDataSource {

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String

    suspend fun clear()

}