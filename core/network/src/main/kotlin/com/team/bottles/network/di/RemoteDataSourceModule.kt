package com.team.bottles.network.di

import com.team.bottles.network.datasource.AuthDataSource
import com.team.bottles.network.datasource.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(dataSourceImpl: AuthDataSourceImpl): AuthDataSource

}