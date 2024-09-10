package com.team.bottles.feat.setting.mvi

import com.team.bottles.core.common.UiState

data class SettingUiState(
    val showDialog: Boolean = false,
    val dialogType: SettingAlertDialogType = SettingAlertDialogType.LOG_OUT,
    val isMatchingActive: Boolean = false,
    val isFloatingBottle: Boolean = false,
    val isGoodFeelingArrived: Boolean = false,
    val isConversation: Boolean = false,
    val isMarketingResponse: Boolean = false,
) : UiState

enum class SettingAlertDialogType(
    val title: String,
    val content: String,
    val dismissText: String,
    val confirmText: String
) {
    LOG_OUT(
        title = "로그아웃",
        content = "정말 로그아웃 하시겠어요?",
        confirmText = "로그아웃하기",
        dismissText = "취소하기"
    ),
    DELETE_USER(
        title = "탈퇴하기",
        content = "탈퇴 시 계정 복구가 어려워요.\n" +
                "정말 탈퇴하시겠어요?",
        confirmText = "탈퇴하기",
        dismissText = "취소하기"
    ),
    ;
}
