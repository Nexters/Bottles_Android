package com.team.bottles.network.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivateMatchingRequest(
    @SerialName("activate") val activate: Boolean
)