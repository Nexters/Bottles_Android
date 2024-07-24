package com.team.bottles.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.team.bottles.core.datastore.UserPreferenceSerializer
import com.team.bottles.core.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPreferenceProtoDataStore(
        @ApplicationContext context: Context,
        userPreferenceSerializer: UserPreferenceSerializer
    ) : DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferenceSerializer,
            produceFile = {
                context.dataStoreFile(USER_PREFERENCE_FILE)
            }
        )

    @Provides
    @Singleton
    fun provideUserPreferenceSerializer(): UserPreferenceSerializer = UserPreferenceSerializer

    companion object {
        private const val USER_PREFERENCE_FILE = "user_preference.json"
    }

}