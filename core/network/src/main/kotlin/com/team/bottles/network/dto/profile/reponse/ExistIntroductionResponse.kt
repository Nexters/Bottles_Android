package com.team.bottles.network.dto.profile.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExistIntroductionResponse(
    @SerialName("isExist") val isExist: Boolean
)