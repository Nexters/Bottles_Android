package com.team.bottles.network.dto.bottle.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterLetterRequest(
    @SerialName("order") val order: Int,
    @SerialName("answer") val answer: String
)