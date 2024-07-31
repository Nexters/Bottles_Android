package com.team.bottles.di.repository

import com.team.bottles.core.data.repository.LoginRepositoryImpl
import com.team.bottles.core.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsLoginRepository(repoImpl: LoginRepositoryImpl): AuthRepository

}