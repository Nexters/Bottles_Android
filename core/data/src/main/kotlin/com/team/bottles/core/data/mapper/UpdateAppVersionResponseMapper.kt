package com.team.bottles.core.data.mapper

import com.team.bottles.network.dto.auth.response.UpdateAppVersionResponse

fun UpdateAppVersionResponse.toLatestVersionCode(): String =
    this.minimumAndroidVersion?: "10007" // TODO : 앱 버전이 null 일경우 처리