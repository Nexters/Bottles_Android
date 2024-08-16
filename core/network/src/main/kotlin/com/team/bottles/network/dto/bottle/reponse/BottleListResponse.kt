package com.team.bottles.network.dto.bottle.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BottleListResponse(
    @SerialName("randomBottles") val randomBottles: List<BottleDto>,
    @SerialName("sentBottles") val sentBottles: List<BottleDto>,
    @SerialName("nextBottleLeftHours") val nextBottleLeftHours: Int,
)

@Serializable
data class BottleDto(
    @SerialName("id") val id: Long,
    @SerialName("userId") val userId: Long,
    @SerialName("userName") val userName: String,
    @SerialName("age") val age: Int,
    @SerialName("mbti") val mbti: String?,
    @SerialName("keyword") val keyword: List<String>?,
    @SerialName("userImageUrl") val userImageUrl: String?,
    @SerialName("expiredAt") val expiredAt: String
)