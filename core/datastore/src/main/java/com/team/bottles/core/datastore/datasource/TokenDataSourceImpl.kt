package com.team.bottles.core.datastore.datasource

import androidx.datastore.core.DataStore
import com.team.bottles.core.datastore.TokenPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val tokenPreferences: DataStore<TokenPreferences>
) : TokenDataSource {

    override suspend fun setAccessToken(accessToken: String) {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .setRefreshToken(refreshToken)
                .build()
        }
    }

    override suspend fun setFcmDeviceToken(fcmDeviceToken: String) {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .setFcmDeviceToken(fcmDeviceToken)
                .build()
        }
    }

    override suspend fun setIsUpdatedFcmToken(isUpdated: Boolean) {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .setIsUpdatedFcmToken(isUpdated)
                .build()
        }
    }

    override suspend fun getAccessToken(): String =
        tokenPreferences.data.map { preferences ->
            preferences.accessToken
        }.first()

    override suspend fun getRefreshToken(): String =
        tokenPreferences.data.map { preferences ->
            preferences.refreshToken
        }.first()

    override suspend fun getFcmDeviceToken(): String =
        tokenPreferences.data.map { preferences ->
            preferences.fcmDeviceToken
        }.first()

    override suspend fun getIsUpdatedFcmToken(): Boolean =
        tokenPreferences.data.map { preferences ->
            preferences.isUpdatedFcmToken
        }.first()

    override suspend fun clearAccessTokenAndRefreshToken() {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .clearAccessToken()
                .clearRefreshToken()
                .build()
        }
    }

    override suspend fun clearIsUpdatedFcmToken() {
        tokenPreferences.updateData { preferences ->
            preferences.toBuilder()
                .clearIsUpdatedFcmToken()
                .build()
        }
    }

}
