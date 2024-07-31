package com.team.bottles.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoSignInUpResponse(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("isSignUp") val isSignUp: Boolean,
    @SerialName("refreshToken") val refreshToken: String
)
