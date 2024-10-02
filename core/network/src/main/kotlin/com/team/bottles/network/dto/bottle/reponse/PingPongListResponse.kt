package com.team.bottles.network.dto.bottle.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PingPongListResponse(
    @SerialName("activeBottles") val activeBottles: List<PingPongBottleDto>,
    @SerialName("doneBottles") val doneBottles: List<PingPongBottleDto>
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
)