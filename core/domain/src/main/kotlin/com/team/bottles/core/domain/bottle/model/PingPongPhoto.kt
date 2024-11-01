package com.team.bottles.core.domain.bottle.model

data class PingPongPhotos(
    val myImageUrl: String = "",
    @Deprecated("이미지 여러장 받는것으로 변경되면서 사용 하지 않는 데이터")
    val otherImageUrl: String = "",
    val otherImageUrls: List<String> = emptyList(),
)

enum class PingPongPhotoStatus {
    NONE, // 아직 사진 교환 상태가 아닐 때
    MY_REJECT, // 내가 거절한 경우
    OTHER_REJECT, // 상대방이 거절한 경우
    REQUIRE_SELECT, // 내가 선택을 해야하는 경우
    WAITING_OTHER_ANSWER, // 내가 답하고 상대방이 답 안한 경우
    BOTH_AGREE, // 모두 동의한 경우
    ;
}
