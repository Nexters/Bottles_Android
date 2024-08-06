package com.team.bottles.core.domain.profile.repository

import com.team.bottles.core.domain.profile.model.QuestionAndAnswer

interface ProfileRepository {

    suspend fun createIntroduction(questionsAndAnswers: List<QuestionAndAnswer>)

}