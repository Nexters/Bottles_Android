package com.team.bottles.network.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthSmsRequest(
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("authCode") val authCode: String,
)