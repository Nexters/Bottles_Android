package com.team.bottles.core.datastore.manager

import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow

interface UserDataStoreManager {

    suspend fun setAccessToken(accessToken: String)

    fun getAccessToken(): Flow<String>

    suspend fun setRefreshToken(refreshToken: String)

    fun getRefreshToken(): Flow<String>

    suspend fun setLocalUserData(localUserData: LocalUserData)

    fun getLocalUserData(): Flow<LocalUserData>

}