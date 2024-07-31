package com.team.bottles.feat.login.di

import android.app.Activity
import com.team.bottles.core.domain.login.kakao.KakaoClient
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ClientEntryPoint {

    fun kakaoClient(): KakaoClient

}

internal val Activity.clientEntryPoint
    get() = EntryPointAccessors.fromActivity(
        this,
        ClientEntryPoint::class.java
    )