package com.team.bottles.core.data.datasource.local

import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {

    fun loadKakaoToken(): Flow<String>

    suspend fun updateKakaoToken(token: String)

    fun loadLocalUserData(): Flow<LocalUserData>

    suspend fun updateLocalUserData(data: LocalUserData)

}