package com.team.bottles.core.data.repository

import com.team.bottles.core.domain.user.repository.UserRepository
import com.team.bottles.local.datasource.DeviceDataSource
import com.team.bottles.network.datasource.UserDataSource
import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val deviceDataSource: DeviceDataSource,
) : UserRepository {

    override suspend fun reportUser(userId: Int, contents: String) {
        userDataSource.sendReportContents(
            request = ReportUserRequest(
                userId = userId,
                reportReasonShortAnswer = contents
            )
        )
    }

    override suspend fun loadContacts(): List<String> =
        deviceDataSource.getContacts()

    override suspend fun updateBlockingContacts(contacts: List<String>) {
        userDataSource.updateWantToBlockContacts(
            request = BlockContactListRequest(
                blockContacts = contacts
            )
        )
    }

}