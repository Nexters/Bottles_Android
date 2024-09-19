package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class GetContactsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): GetContactsUseCase {

    override suspend fun invoke(): List<String> =
        userRepository.loadContacts()

}

interface GetContactsUseCase {

    suspend operator fun invoke(): List<String>

}