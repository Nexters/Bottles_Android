package com.team.bottles.di.usecase

import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCase
import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCaseImpl
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCaseImpl
import com.team.bottles.core.domain.profile.usecase.UploadProfileImageUseCase
import com.team.bottles.core.domain.profile.usecase.UploadProfileImageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileUseCaseModule {

    @Binds
    abstract fun bindsCreateIntroductionUseCase(
        useCaseImpl: CreateIntroductionUseCaseImpl
    ): CreateIntroductionUseCase

    @Binds
    abstract fun bindsUploadProfileImageUseCase(
        useCaseImpl: UploadProfileImageUseCaseImpl
    ): UploadProfileImageUseCase

    @Binds
    abstract fun bindsGetUserProfileUseCase(
        useCaseImpl: GetUserProfileUseCaseImpl
    ): GetUserProfileUseCase

}