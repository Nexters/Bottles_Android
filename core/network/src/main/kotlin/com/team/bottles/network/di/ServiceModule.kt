package com.team.bottles.network.di

import com.team.bottles.network.api.AuthService
import com.team.bottles.network.api.BottleService
import com.team.bottles.network.api.ProfileService
import com.team.bottles.network.api.UserService
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

    @Provides
    fun providesSecureProfileService(@Named("Secure") retrofit: Retrofit): ProfileService = retrofit.create()

    @Provides
    fun providesSecureBottleService(@Named("Secure") retrofit: Retrofit): BottleService = retrofit.create()

    @Provides
    fun providesSecureUserService(@Named("Secure") retrofit: Retrofit): UserService = retrofit.create()

}
