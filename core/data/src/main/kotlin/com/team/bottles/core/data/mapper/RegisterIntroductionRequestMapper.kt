package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.network.dto.profile.reponse.QuestionAndAnswerDTO
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest

fun List<QuestionAndAnswer>.toRequest(): RegisterIntroductionRequest =
    RegisterIntroductionRequest(
        introduction = this.map { it.toDto() }
    )

fun QuestionAndAnswer.toDto(): QuestionAndAnswerDTO =
    QuestionAndAnswerDTO(
        answer = this.answer,
        question = this.question
    )