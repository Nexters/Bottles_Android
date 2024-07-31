package com.team.bottles.di.datasource

import com.team.bottles.core.data.datasource.remote.AuthDataSource
import com.team.bottles.core.data.datasource.remote.AuthDataSourceImpl
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