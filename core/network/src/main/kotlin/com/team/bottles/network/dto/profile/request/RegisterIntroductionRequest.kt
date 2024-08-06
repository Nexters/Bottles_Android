package com.team.bottles.network.dto.profile.request

import com.team.bottles.network.dto.profile.reponse.QuestionAndAnswerDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterIntroductionRequest(
    @SerialName("introduction") val introduction: List<QuestionAndAnswerDTO>
)
