package com.team.bottles.network.dto.profile.reponse

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
)