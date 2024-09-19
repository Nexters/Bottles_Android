package com.team.bottles.network.dto.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlimyResponse(
    @SerialName("alimyType") val alimyType: AlimyType,
    @SerialName("enabled") val enabled: Boolean
)

@Serializable
enum class AlimyType {
    @SerialName("DAILY_RANDOM") DAILY_RANDOM,
    @SerialName("RECEIVE_LIKE") RECEIVE_LIKE,
    @SerialName("PINGPONG") PING_PONG,
    @SerialName("MARKETING") MARKETING,
    ;
}