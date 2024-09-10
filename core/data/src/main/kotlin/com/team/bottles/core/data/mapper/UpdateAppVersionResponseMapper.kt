package com.team.bottles.core.data.mapper

import com.team.bottles.network.dto.auth.response.UpdateAppVersionResponse

fun UpdateAppVersionResponse.toLatestVersionCode(): String =
    this.minimumAndroidVersion?: "10007" // TODO : latestAndroidVersion 나오면 변경

fun UpdateAppVersionResponse.toMinimumVersionCode(): String =
    this.minimumAndroidVersion?: "10007"