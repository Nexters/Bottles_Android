package com.team.bottles.network.dto.bottle.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PingPongListResponse(
    @SerialName("pingPongBottles") val pingPongBottles: List<PingPongBottleDto>,
)

@Serializable
data class PingPongBottleDto(
    @SerialName("id") val id: Long,
    @SerialName("isRead") val isRead: Boolean,
    @SerialName("userName") val userName: String,
    @SerialName("age") val age: Int,
    @SerialName("mbti") val mbti: String?,
    @SerialName("keyword") val keyword: List<String>?,
    @SerialName("userImageUrl") val userImageUrl: String?,
    @SerialName("userId") val userId: Long,
    @SerialName("lastActivatedAt") val lastActivatedAt: String,
    @SerialName("lastStatus") val lastStatus: LastStatusDto,
)

@Serializable
enum class LastStatusDto(val status: String) {
    @SerialName("ANSWER_FROM_ME_ONLY") ANSWER_FROM_ME_ONLY(status = "문답을 보냈어요"),
    @SerialName("ANSWER_FROM_OTHER") ANSWER_FROM_OTHER(status = "새로운 문답이 도착했어요"),
    @SerialName("CONTACT_SHARED_BY_ME_ONLY") CONTACT_SHARED_BY_ME_ONLY(status = "연락처를 공유했어요"),
    @SerialName("CONTACT_SHARED_BY_OTHER") CONTACT_SHARED_BY_OTHER(status = "연락처가 도착했어요"),
    @SerialName("CONVERSATION_STOPPED") CONVERSATION_STOPPED(status = "대화가 중단됐어요"),
    @SerialName("NO_ANSWER_FROM_BOTH") NO_ANSWER_FROM_BOTH(status = "문답을 시작해 주세요"),
    @SerialName("PHOTO_SHARED_BY_ME_ONLY") PHOTO_SHARED_BY_ME_ONLY(status = "사진을 공유했어요"),
    @SerialName("PHOTO_SHARED_BY_OTHER") PHOTO_SHARED_BY_OTHER(status = "사진이 도착했어요"),
    ;
}