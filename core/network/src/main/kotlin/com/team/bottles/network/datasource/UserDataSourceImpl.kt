package com.team.bottles.network.datasource

import com.team.bottles.network.api.UserService
import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
): UserDataSource {

    override suspend fun sendReportContents(request: ReportUserRequest) {
        userService.postReportUser(request = request)
    }

    override suspend fun updateWantToBlockContacts(request: BlockContactListRequest) {
        userService.postBlockedContacts(request = request)
    }

}