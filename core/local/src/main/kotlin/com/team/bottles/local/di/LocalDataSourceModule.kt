package com.team.bottles.local.di

import com.team.bottles.local.datasource.DeviceDataSource
import com.team.bottles.local.datasource.DeviceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsDeviceDataSource(dataSourceImpl: DeviceDataSourceImpl): DeviceDataSource

}