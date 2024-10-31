package com.team.bottles.network.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NativeSettingRegisterRequest(
    @SerialName("alimyTurnedOn") val alimyTurnedOn: Boolean,
    @SerialName("appVersion") val appVersion: String,
    @SerialName("deviceId") val deviceId: String,
    @SerialName("deviceName") val deviceName: String
)