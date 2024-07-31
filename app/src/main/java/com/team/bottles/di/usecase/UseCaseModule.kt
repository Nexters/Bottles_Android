package com.team.bottles.di.usecase

import com.team.bottles.core.domain.login.kakao.usecase.LoginWithKakaoUseCase
import com.team.bottles.core.domain.login.kakao.usecase.LoginWithKakaoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsLoginWithKaKaoUseCase(
        useCaseImpl: LoginWithKakaoUseCaseImpl
    ): LoginWithKakaoUseCase

}