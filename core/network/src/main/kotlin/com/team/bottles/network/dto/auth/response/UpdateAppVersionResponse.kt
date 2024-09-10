package com.team.bottles.network.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAppVersionResponse(
    @SerialName("minimumAndroidVersion") val minimumAndroidVersion: String?
)