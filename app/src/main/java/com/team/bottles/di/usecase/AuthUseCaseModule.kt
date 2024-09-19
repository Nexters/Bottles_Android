package com.team.bottles.di.usecase

import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCase
import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCaseImpl
import com.team.bottles.core.domain.auth.usecase.GetLatestAppVersionUseCase
import com.team.bottles.core.domain.auth.usecase.GetLatestAppVersionUseCaseImpl
import com.team.bottles.core.domain.auth.usecase.GetRequiredAppVersionUseCase
import com.team.bottles.core.domain.auth.usecase.GetRequiredAppVersionUseCaseImpl
import com.team.bottles.core.domain.auth.usecase.LogOutUseCase
import com.team.bottles.core.domain.auth.usecase.LogOutUseCaseImpl
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

    @Binds
    abstract fun bindsLogOutUseCase(
        useCaseImpl: LogOutUseCaseImpl
    ): LogOutUseCase

    @Binds
    abstract fun bindsDeleteUserUseCase(
        useCaseImpl: DeleteUserUseCaseImpl
    ): DeleteUserUseCase

    @Binds
    abstract fun bindsGetLatestAppVersionUseCase(
        useCaseImpl: GetLatestAppVersionUseCaseImpl
    ): GetLatestAppVersionUseCase

    @Binds
    abstract fun bindsGetRequiredAppVersionUseCase(
        useCaseImpl: GetRequiredAppVersionUseCaseImpl
    ): GetRequiredAppVersionUseCase

}