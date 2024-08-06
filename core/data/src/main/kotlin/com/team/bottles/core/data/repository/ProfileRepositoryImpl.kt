package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toRequest
import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.repository.ProfileRepository
import com.team.bottles.network.datasource.ProfileDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override suspend fun createIntroduction(questionsAndAnswers: List<QuestionAndAnswer>) {
        profileDataSource.createIntroduction(request = questionsAndAnswers.toRequest())
    }

}