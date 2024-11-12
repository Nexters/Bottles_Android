package com.team.bottles.network.datasource

import com.team.bottles.network.api.UserService
import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ActivateMatchingRequest
import com.team.bottles.network.dto.user.request.AlimyOnOffRequest
import com.team.bottles.network.dto.user.request.NativeSettingRegisterRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import com.team.bottles.network.dto.user.response.AlimyResponse
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

    override suspend fun fetchSettingNotifications(): List<AlimyResponse> =
        userService.getSettingNotifications()

    override suspend fun updateSettingNotification(request: AlimyOnOffRequest) {
        userService.postSettingNotification(request = request)
    }

    override suspend fun updateActivateMatching(request: ActivateMatchingRequest) {
        userService.postActivateMatching(request = request)
    }

    override suspend fun sendCurrentSystemNotificationState(request: NativeSettingRegisterRequest) {
        userService.postCurrentSystemNotificationState(request = request)
    }

}