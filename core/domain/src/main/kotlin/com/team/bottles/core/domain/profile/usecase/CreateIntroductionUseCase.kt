package com.team.bottles.core.domain.profile.usecase

import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class CreateIntroductionUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
) : CreateIntroductionUseCase {

    override suspend fun invoke(questionsAndAnswers: List<QuestionAndAnswer>) {
        profileRepository.createIntroduction(questionsAndAnswers = questionsAndAnswers)
    }

}

interface CreateIntroductionUseCase {

    suspend operator fun invoke(questionsAndAnswers: List<QuestionAndAnswer>)

}