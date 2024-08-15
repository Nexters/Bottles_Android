package com.team.bottles.network.dto.bottle.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BottleImageShareRequest(
    @SerialName("willShare") val willShare: Boolean
)