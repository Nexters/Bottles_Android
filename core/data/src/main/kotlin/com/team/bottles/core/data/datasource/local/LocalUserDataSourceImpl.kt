package com.team.bottles.core.data.datasource.local

import com.team.bottles.core.datastore.manager.UserDataStoreManager
import com.team.bottles.core.datastore.model.LocalUserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDataStoreManager: UserDataStoreManager
): LocalUserDataSource {

    override fun loadKakaoToken(): Flow<String> =
        userDataStoreManager.getKakaoToken()

    override suspend fun updateKakaoToken(token: String) {
        userDataStoreManager.setKakaoToken(token = token)
    }

    override fun loadLocalUserData(): Flow<LocalUserData> =
        userDataStoreManager.getLocalUserData()

    override suspend fun updateLocalUserData(data: LocalUserData) {
        userDataStoreManager.setLocalUserData(data)
    }

}