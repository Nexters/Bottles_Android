package com.team.bottles.di.datasource

import com.team.bottles.core.data.datasource.local.LocalUserDataSource
import com.team.bottles.core.data.datasource.local.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsLocalUserDataSource(dataSourceImpl: LocalUserDataSourceImpl): LocalUserDataSource

}