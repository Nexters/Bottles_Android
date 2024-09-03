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
) : AlertInfo {
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
    STOP_PING_PONG(
        title = "중단하기",
        content = "중단 시 모든 핑퐁 내용이 사라져요.\n" +
                "정말 중단하시겠어요?",
        confirmText = "중단하기",
        dismissText = "계속하기"
    ),
    USER_REPORT(
        title = "신고하기",
        content = "접수 후 취소할 수 없으며\n" +
                "해당 사용자는 차단돼요.\n" +
                "정말 신고하시겠어요?",
        confirmText = "신고하기",
        dismissText = "계속하기"
    )
    ;
}
