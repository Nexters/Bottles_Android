package com.team.bottles.core.domain.user.repository

import com.team.bottles.core.domain.user.model.Notification

interface UserRepository {

    suspend fun reportUser(
        userId: Int,
        contents: String
    )

    suspend fun loadContacts(): List<String>

    suspend fun updateBlockingContacts(contacts: List<String>)

    suspend fun loadSettingNotifications(): List<Notification>

    suspend fun updateSettingNotification(notification: Notification)

    suspend fun updateActivateMatching(isActivate: Boolean)

    suspend fun getNotificationPermissionStatus(): Boolean

}