package com.team.bottles.di.usecase

import com.team.bottles.core.domain.auth.usecase.LoginWithKakaoUseCase
import com.team.bottles.core.domain.auth.usecase.LoginWithKakaoUseCaseImpl
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCaseImpl
import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCase
import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCaseImpl
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

    @Binds
    abstract fun bindsWebViewConnectUseCase(
        useCaseImpl: WebViewConnectUseCaseImpl
    ): WebViewConnectUseCase

    @Binds
    abstract fun bindsCreateIntroductionUseCase(
        useCaseImpl: CreateIntroductionUseCaseImpl
    ): CreateIntroductionUseCase

}