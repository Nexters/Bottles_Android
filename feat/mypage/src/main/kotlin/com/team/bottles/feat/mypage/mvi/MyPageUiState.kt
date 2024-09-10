package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.feat.mypage.BuildConfig

data class MyPageUiState(
    val showDialog: Boolean = false,
    val imageUrl: String = "",
    val userName: String = "",
    val userAge: Int = 0,
    val blockedUserValue: Int = 0,
    val appVersionName: String = BuildConfig.VERSION_NAME,
    val appVersionCode: String = BuildConfig.VERSION_CODE,
    val canUpdateAppVersion: Boolean = false,
    val inDeviceContacts: Int = 0,
) : UiState
