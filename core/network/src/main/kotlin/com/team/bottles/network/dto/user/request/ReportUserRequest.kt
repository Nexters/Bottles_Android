package com.team.bottles.network.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportUserRequest(
    @SerialName("reportReasonShortAnswer") val reportReasonShortAnswer: String,
    @SerialName("userId") val userId: Int,
)