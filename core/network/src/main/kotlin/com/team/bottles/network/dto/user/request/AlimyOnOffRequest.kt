package com.team.bottles.network.dto.user.request

import com.team.bottles.network.dto.user.response.AlimyType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlimyOnOffRequest(
    @SerialName("alimyType") val alimyType: AlimyType,
    @SerialName("enabled") val enabled: Boolean
)