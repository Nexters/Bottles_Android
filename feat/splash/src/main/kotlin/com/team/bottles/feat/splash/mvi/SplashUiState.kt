package com.team.bottles.feat.splash.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.feat.splash.BuildConfig

data class SplashUiState(
    val appVersionCode: Int = BuildConfig.VERSION_CODE,
    val showDialog: Boolean = false,
    val isError: Boolean = false
) : UiState