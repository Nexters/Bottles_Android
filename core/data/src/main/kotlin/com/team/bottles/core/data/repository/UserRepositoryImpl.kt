package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toAlimyType
import com.team.bottles.core.data.mapper.toNotification
import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.repository.UserRepository
import com.team.bottles.local.datasource.DeviceDataSource
import com.team.bottles.network.datasource.UserDataSource
import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ActivateMatchingRequest
import com.team.bottles.network.dto.user.request.AlimyOnOffRequest
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

    override suspend fun loadSettingNotifications(): List<Notification> =
        userDataSource.fetchSettingNotifications().map { response ->
            response.toNotification()
        }

    override suspend fun updateSettingNotification(notification: Notification) {
        userDataSource.updateSettingNotification(
            request = AlimyOnOffRequest(
                alimyType = notification.notificationType.toAlimyType(),
                enabled = notification.enabled
            )
        )
    }

    override suspend fun updateActivateMatching(isActivate: Boolean) {
        userDataSource.updateActivateMatching(request = ActivateMatchingRequest(activate = isActivate))
    }

    override suspend fun getNotificationPermissionStatus(): Boolean =
        deviceDataSource.getIsAllowedNotificationPermission()

}