package com.team.bottles.core.data.datasource.local

import com.team.bottles.core.datastore.manager.UserDataStoreManager
import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDataStoreManager: UserDataStoreManager
) : LocalUserDataSource {

    override fun fetchAccessToken(): Flow<String> =
        userDataStoreManager.getAccessToken()

    override suspend fun updateAccessToken(accessToken: String) {
        userDataStoreManager.setAccessToken(accessToken = accessToken)
    }

    override fun fetchRefreshToken(): Flow<String> =
        userDataStoreManager.getRefreshToken()

    override suspend fun updateRefreshToken(refreshToken: String) {
        userDataStoreManager.setRefreshToken(refreshToken = refreshToken)
    }

    override fun fetchLocalUserData(): Flow<LocalUserData> =
        userDataStoreManager.getLocalUserData()

    override suspend fun updateLocalUserData(data: LocalUserData) {
        userDataStoreManager.setLocalUserData(data)
    }

}