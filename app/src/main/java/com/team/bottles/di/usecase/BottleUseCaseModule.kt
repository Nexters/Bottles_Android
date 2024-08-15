package com.team.bottles.di.usecase

import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCase
import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCase
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongShareKakaoIdUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongShareKakaoIdUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongSharePhotoUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongSharePhotoUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.SendPingPongLetterUseCase
import com.team.bottles.core.domain.bottle.usecase.SendPingPongLetterUseCaseImpl
import com.team.bottles.core.domain.bottle.usecase.StopPingPongUseCase
import com.team.bottles.core.domain.bottle.usecase.StopPingPongUseCaseImpl
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

    @Binds
    @Singleton
    abstract fun bindsSendPingPongLetterUseCase(
        useCaseImpl: SendPingPongLetterUseCaseImpl
    ): SendPingPongLetterUseCase

    @Binds
    @Singleton
    abstract fun bindsSelectPingPongSharePhotoUseCase(
        useCaseImpl: SelectPingPongSharePhotoUseCaseImpl
    ): SelectPingPongSharePhotoUseCase

    @Binds
    @Singleton
    abstract fun bindsSelectPingPongShareKakaoIdUseCase(
        useCaseImpl: SelectPingPongShareKakaoIdUseCaseImpl
    ): SelectPingPongShareKakaoIdUseCase

    @Binds
    @Singleton
    abstract fun bindsStopPingPongUseCase(
        useCaseImpl: StopPingPongUseCaseImpl
    ): StopPingPongUseCase

}