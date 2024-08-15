package com.team.bottles.core.ui.model

interface AlertInfo {

    val confirmText: String

    val dismissText: String

    val title: String

    val content: String

}

enum class AlertType(
    override val confirmText: String,
    override val dismissText: String,
    override val title: String,
    override val content: String
): AlertInfo {
    LOG_OUT(
        title = "로그아웃",
        content = "정말 로그아웃 하시겠어요?",
        confirmText = "로그아웃",
        dismissText = "취소하기"
    ),
    DELETE_USER(
        title = "탈퇴하기",
        content = "탈퇴 시 계정 복구가 어려워요.\n정말 탈퇴하시겠어요?",
        confirmText = "탈퇴하기",
        dismissText = "취소하기"
    ),
    STOP_PING_PONG(
        title = "중단하기",
        content = "중단 시 모든 핑퐁 내용이 사라져요.\n정말 중단하시겠어요?",
        confirmText = "중단하기",
        dismissText = "계속하기"
    ),
    ;
}
