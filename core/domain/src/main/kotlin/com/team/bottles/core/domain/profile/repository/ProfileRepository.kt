package com.team.bottles.core.domain.profile.repository

import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import java.io.File

interface ProfileRepository {

    suspend fun createIntroduction(questionsAndAnswers: List<QuestionAndAnswer>)

    suspend fun uploadProfileImage(imageFile: File)

}