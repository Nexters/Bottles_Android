package com.team.bottles.core.domain.bottle.model

data class PingPongMatchResult(
    val matchStatus: MatchStatus = MatchStatus.IN_CONVERSATION,
    val otherContact: String = "",
    val shouldAnswer: Boolean = true,
    val isFirstSelect: Boolean = false,
)

enum class MatchStatus {
    IN_CONVERSATION,
    MATCH_FAILED,
    MATCH_SUCCEEDED,
    ;
}