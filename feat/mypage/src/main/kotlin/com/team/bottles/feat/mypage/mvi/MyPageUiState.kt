package com.team.bottles.feat.mypage.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.feat.mypage.BuildConfig

data class MyPageUiState(
    val showAccessPermissionGuideDialog: Boolean = false,
    val showBlockContactsDialog: Boolean = false,
    val imageUrl: String = "",
    val userName: String = "",
    val userAge: Int = 0,
    val blockedUserValue: Int = 0,
    val appVersionName: String = BuildConfig.VERSION_NAME,
    val appVersionCode: Int = BuildConfig.VERSION_CODE,
    val canUpdateAppVersion: Boolean = false,
    val inDeviceContacts: List<String> = emptyList(),
    val myPageState: MyPageState = MyPageState.INIT,
    val isError: Boolean = false,
) : UiState {

    enum class MyPageState {
        INIT,
        UPDATE_BLOCK_CONTACTS,
        ;
    }

}
