package com.team.bottles.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.team.bottles.core.datastore.TokenPreferences
import com.team.bottles.core.datastore.TokenPreferencesSerializer
import com.team.bottles.core.datastore.UserPreferences
import com.team.bottles.core.datastore.UserPreferencesSerializer
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
    fun provideUserPreferencesProtoDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            produceFile = { context.dataStoreFile(USER_PREFERENCES_FILE) }
        )

    @Provides
    @Singleton
    fun provideTokenPreferencesProtoDataStore(
        @ApplicationContext context: Context,
        tokenPreferencesSerializer: TokenPreferencesSerializer
    ): DataStore<TokenPreferences> =
        DataStoreFactory.create(
            serializer = tokenPreferencesSerializer,
            produceFile = { context.dataStoreFile(TOKEN_PREFERENCES_FILE) }
        )

    @Provides
    @Singleton
    fun provideUserPreferencesSerializer(): UserPreferencesSerializer = UserPreferencesSerializer

    @Provides
    @Singleton
    fun provideTokenPreferencesSerializer(): TokenPreferencesSerializer = TokenPreferencesSerializer

    companion object {
        private const val USER_PREFERENCES_FILE = "user_preferences.json"
        private const val TOKEN_PREFERENCES_FILE = "token_preferences.json"
    }

}