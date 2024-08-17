package com.team.bottles.network.di

import com.team.bottles.network.datasource.AuthDataSource
import com.team.bottles.network.datasource.AuthDataSourceImpl
import com.team.bottles.network.datasource.BottleDataSource
import com.team.bottles.network.datasource.BottleDataSourceImpl
import com.team.bottles.network.datasource.ProfileDataSource
import com.team.bottles.network.datasource.ProfileDataSourceImpl
import com.team.bottles.network.datasource.UserDataSource
import com.team.bottles.network.datasource.UserDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindsProfileDataSource(dataSourceImpl: ProfileDataSourceImpl): ProfileDataSource

    @Binds
    @Singleton
    abstract fun bindsBottleDataSource(dataSourceImpl: BottleDataSourceImpl): BottleDataSource

    @Binds
    @Singleton
    abstract fun bindsUserDataSource(dataSourceImpl: UserDataSourceImpl): UserDataSource

}