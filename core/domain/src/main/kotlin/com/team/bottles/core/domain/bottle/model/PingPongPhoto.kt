package com.team.bottles.core.domain.bottle.model

data class PingPongPhoto(
    val myImageUrl: String? = null,
    val otherImageUrl: String? = null,
    val photoCardStatus: PhotoCardStatus = PhotoCardStatus.NONE
)

enum class PhotoCardStatus {
    NONE,
    MY_REJECT,
    OTHER_REJECT,
    REQUIRE_SELECT,
    WAITING_OTHER_ANSWER,
    BOTH_AGREE,
    ;
}