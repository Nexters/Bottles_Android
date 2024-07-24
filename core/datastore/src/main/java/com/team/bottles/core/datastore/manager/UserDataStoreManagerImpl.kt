package com.team.bottles.core.datastore.manager

import android.util.Log
import androidx.datastore.core.DataStore
import com.team.bottles.core.datastore.UserPreferences
import com.team.bottles.core.datastore.model.LocalUserData
import com.team.bottles.core.datastore.model.mapper.toDataStore
import com.team.bottles.core.datastore.model.mapper.toLocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreManagerImpl @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) : UserDataStoreManager {

    override suspend fun setKakaoToken(token: String): String {
        val updatedPreferences = userPreferences.updateData { preferences ->
            preferences.toBuilder()
                .setKakaoToken(token)
                .build()
        }
        Log.d("매니저", updatedPreferences.toString())

        return updatedPreferences.kakaoToken
    }

    override fun getKakaoToken(): Flow<String> =
        userPreferences.data.map { preferences ->
            preferences.kakaoToken
        }

    override fun getLocalUserData(): Flow<LocalUserData> =
        userPreferences.data.map { preferences ->
            LocalUserData(
                name = preferences.userName,
                age = preferences.age,
                introductions = preferences.introductionsList.toLocalData(),
                profileSelect = preferences.profileSelect.toLocalData()
            )
        }

    override suspend fun setLocalUserData(localUserData: LocalUserData) {
        userPreferences.updateData { preferences->
            preferences.toBuilder()
                .setUserName(localUserData.name)
                .setAge(localUserData.age)
                .addAllIntroductions(localUserData.introductions.toDataStore())
                .setProfileSelect(localUserData.profileSelect.toDataStore())
                .build()
        }
    }

}