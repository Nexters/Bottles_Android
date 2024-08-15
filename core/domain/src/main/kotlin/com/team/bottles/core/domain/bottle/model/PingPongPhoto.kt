package com.team.bottles.core.domain.bottle.model

data class PingPongPhotos(
    val myImageUrl: String = "",
    val otherImageUrl: String = "",
)

enum class PingPongPhotoStatus {
    NONE, // 아직 사진 교환 상태가 아닐 때
    MY_REJECT, // 내가 거절한 경우
    OTHER_REJECT, // 상대방이 거절한 경우
    REQUIRE_SELECT_OTHER_SELECT, // 상대방이 답하고 내가 답 안한 경우
    REQUIRE_SELECT_OTHER_NOT_SELECT, // 상대방이 답 안하고 내가 답 안한 경우
    WAITING_OTHER_ANSWER, // 내가 답하고 상대방이 답 안한 경우
    BOTH_AGREE, // 모두 동의한 경우
    ;
}
