package com.team.bottles.di.usecase

import com.team.bottles.core.domain.auth.usecase.LoginWithKakaoUseCase
import com.team.bottles.core.domain.auth.usecase.LoginWithKakaoUseCaseImpl
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthUseCaseModule {

    @Binds
    abstract fun bindsLoginWithKaKaoUseCase(
        useCaseImpl: LoginWithKakaoUseCaseImpl
    ): LoginWithKakaoUseCase

    @Binds
    abstract fun bindsWebViewConnectUseCase(
        useCaseImpl: WebViewConnectUseCaseImpl
    ): WebViewConnectUseCase

}