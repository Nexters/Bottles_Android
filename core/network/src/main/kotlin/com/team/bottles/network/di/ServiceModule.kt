package com.team.bottles.network.di

import com.team.bottles.network.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesDefaultAuthService(@Named("Default") retrofit: Retrofit): AuthService = retrofit.create()

}
