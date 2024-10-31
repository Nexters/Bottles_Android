package com.team.bottles.core.data.repository

import android.content.Context
import com.team.bottles.core.data.mapper.toAlimyType
import com.team.bottles.core.data.mapper.toNotification
import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.repository.UserRepository
import com.team.bottles.local.datasource.DeviceDataSource
import com.team.bottles.network.datasource.UserDataSource
import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.ActivateMatchingRequest
import com.team.bottles.network.dto.user.request.AlimyOnOffRequest
import com.team.bottles.network.dto.user.request.NativeSettingRegisterRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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

    override suspend fun updateCurrentSystemNotificationState() {
        userDataSource.sendCurrentSystemNotificationState(request =
            NativeSettingRegisterRequest(
                alimyTurnedOn = deviceDataSource.getIsAllowedNotificationPermission(),
                appVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName,
                deviceId = deviceDataSource.getDeviceId(),
                deviceName = deviceDataSource.getDeviceName()
            )
        )
    }

}