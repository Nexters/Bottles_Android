package com.team.bottles.network.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoSignInUpRequest(
    @SerialName("code") val code: String
)
