package com.team.bottles.core.domain.user.usecase

import com.team.bottles.core.domain.user.repository.UserRepository
import javax.inject.Inject

class UpdateBlockingContactsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
): UpdateBlockingContactsUseCase {

    override suspend fun invoke(contacts: List<String>) {
        userRepository.updateBlockingContacts(contacts = contacts)
    }

}

interface UpdateBlockingContactsUseCase {

    suspend operator fun invoke(contacts: List<String>)

}