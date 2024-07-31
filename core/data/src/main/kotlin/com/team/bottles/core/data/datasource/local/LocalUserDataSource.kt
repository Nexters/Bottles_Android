package com.team.bottles.core.data.datasource.local

import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {

    fun fetchAccessToken(): Flow<String>

    suspend fun updateAccessToken(accessToken: String)

    fun fetchRefreshToken(): Flow<String>

    suspend fun updateRefreshToken(refreshToken: String)

    fun fetchLocalUserData(): Flow<LocalUserData>

    suspend fun updateLocalUserData(data: LocalUserData)

}