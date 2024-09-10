package com.team.bottles.core.domain.user.repository

interface UserRepository {

    suspend fun reportUser(
        userId: Int,
        contents: String
    )

    suspend fun loadContacts(): List<String>

    suspend fun updateBlockingContacts(contacts: List<String>)

}