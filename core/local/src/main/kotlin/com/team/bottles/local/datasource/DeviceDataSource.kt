package com.team.bottles.local.datasource

interface DeviceDataSource {

    suspend fun getContacts(): List<String>

    fun getIsAllowedNotificationPermission(): Boolean

    suspend fun getDeviceId(): String

    suspend fun getDeviceName(): String

}