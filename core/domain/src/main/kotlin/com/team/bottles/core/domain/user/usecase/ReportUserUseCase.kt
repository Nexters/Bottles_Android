package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class ReportUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): ReportUserUseCase {

    override suspend fun invoke(userId: Int, contents: String) {
        userRepository.reportUser(userId = userId, contents = contents)
    }

}

interface ReportUserUseCase {

    suspend operator fun invoke(userId: Int, contents: String)

}