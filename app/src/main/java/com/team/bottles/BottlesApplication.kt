package com.team.bottles

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BottlesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKakaoLogin()
    }

    private fun initKakaoLogin() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

}