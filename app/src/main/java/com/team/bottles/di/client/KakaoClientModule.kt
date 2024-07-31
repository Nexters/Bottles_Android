package com.team.bottles.di.client

import com.team.bottles.client.KakaoClientImpl
import com.team.bottles.core.domain.auth.KakaoClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class KakaoClientModule {

    @Binds
    abstract fun bindsKakaoClient(kakaoClientImpl: KakaoClientImpl): KakaoClient

}