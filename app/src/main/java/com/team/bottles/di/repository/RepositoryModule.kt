package com.team.bottles.di.repository

import com.team.bottles.core.data.repository.AuthRepositoryImpl
import com.team.bottles.core.data.repository.BottleRepositoryImpl
import com.team.bottles.core.data.repository.ProfileRepositoryImpl
import com.team.bottles.core.data.repository.UserRepositoryImpl
import com.team.bottles.core.domain.auth.repository.AuthRepository
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import com.team.bottles.core.domain.user.repository.UserRepository
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
    abstract fun bindsLoginRepository(repoImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindsProfileRepository(repoImpl: ProfileRepositoryImpl): ProfileRepository

    @Singleton
    @Binds
    abstract fun bindsBottleRepository(repoImpl: BottleRepositoryImpl): BottleRepository

    @Singleton
    @Binds
    abstract fun bindsUserRepository(repoImpl: UserRepositoryImpl): UserRepository

}