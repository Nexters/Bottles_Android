package com.team.bottles.network.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FcmUpdateRequest(
    @SerialName("fcmToken") val fcmToken: String
)