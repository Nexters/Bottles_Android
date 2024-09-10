package com.team.bottles.local.datasource

interface DeviceDataSource {

    suspend fun getContacts(): List<String>

}