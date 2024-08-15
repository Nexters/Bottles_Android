package com.team.bottles.core.domain.bottle.model

data class PingPongMatchResult(
    val otherContact: String = "",
    val isFirstSelect: Boolean = false,
)

enum class PingPongMatchStatus {
    NONE, // 아직 최종 선택 단계가 아닐 때
    REQUIRE_SELECT, // 최종 선택을 해야할 때
    WAITING_OTHER_ANSWER, // 상대의 답변을 기다려야 할 때
    MATCH_FAILED, // 매칭 실패 했을 때
    MATCH_SUCCEEDED, // 매칭 성공 했을 때
    ;
}