package com.team.bottles.network.datasource

import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ActivateMatchingRequest
import com.team.bottles.network.dto.user.request.AlimyOnOffRequest
import com.team.bottles.network.dto.user.request.NativeSettingRegisterRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import com.team.bottles.network.dto.user.response.AlimyResponse

interface UserDataSource {

    suspend fun sendReportContents(request: ReportUserRequest)

    suspend fun updateWantToBlockContacts(request: BlockContactListRequest)

    suspend fun fetchSettingNotifications(): List<AlimyResponse>

    suspend fun updateSettingNotification(request: AlimyOnOffRequest)

    suspend fun updateActivateMatching(request: ActivateMatchingRequest)

    suspend fun sendCurrentSystemNotificationState(request: NativeSettingRegisterRequest)

}