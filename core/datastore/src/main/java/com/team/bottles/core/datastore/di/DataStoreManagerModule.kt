package com.team.bottles.core.datastore.di

import com.team.bottles.core.datastore.manager.UserDataStoreManager
import com.team.bottles.core.datastore.manager.UserDataStoreManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreManagerModule {

    @Binds
    @Singleton
    abstract fun bindUserDataStoreManager(userDataStoreManagerImpl: UserDataStoreManagerImpl): UserDataStoreManager

}