package com.team.bottles.core.datastore.manager

import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow

interface UserDataStoreManager {

    suspend fun setKakaoToken(token: String): String

    fun getKakaoToken(): Flow<String>

    fun getLocalUserData(): Flow<LocalUserData>

    suspend fun setLocalUserData(localUserData: LocalUserData)

}