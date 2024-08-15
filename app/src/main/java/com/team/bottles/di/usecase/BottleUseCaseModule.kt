package com.team.bottles.di.usecase

import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCase
import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCase
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BottleUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsGetPingPongListUseCase(
        useCaseImpl: GetPingPongListUseCaseImpl
    ): GetPingPongListUseCase

    @Binds
    @Singleton
    abstract fun bindsGetPingPongDetailUseCase(
        useCaseImpl: GetPingPongDetailUseCaseImpl
    ): GetPingPongDetailUseCase

}