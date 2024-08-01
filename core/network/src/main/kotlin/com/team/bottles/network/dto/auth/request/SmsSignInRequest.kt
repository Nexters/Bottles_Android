package com.team.bottles.network.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmsSignInRequest(
    @SerialName("authCode") val authCode: String,
    @SerialName("phoneNumber") val phoneNumber: String,
)