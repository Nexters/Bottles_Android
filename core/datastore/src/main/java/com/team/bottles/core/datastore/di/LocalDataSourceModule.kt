package com.team.bottles.core.datastore.di

import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.core.datastore.datasource.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsTokenDataSource(dataSourceImpl: TokenDataSourceImpl): TokenDataSource

}