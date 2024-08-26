package com.team.bottles.core.datastore.datasource

interface TokenDataSource {

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun setFcmDeviceToken(fcmDeviceToken: String)

    suspend fun setIsUpdatedFcmToken(isUpdated: Boolean)

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String

    suspend fun getFcmDeviceToken(): String

    suspend fun getIsUpdatedFcmToken(): Boolean

    suspend fun clearAccessTokenAndRefreshToken()

    suspend fun clearIsUpdatedFcmToken()

}