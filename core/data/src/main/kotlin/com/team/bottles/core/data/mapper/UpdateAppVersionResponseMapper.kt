package com.team.bottles.core.data.mapper

import com.team.bottles.network.dto.auth.response.UpdateAppVersionResponse

fun UpdateAppVersionResponse.toLatestVersionCode(): Int =
    this.latestAndroidVersion?: 10007

fun UpdateAppVersionResponse.toMinimumVersionCode(): Int =
    this.minimumAndroidVersion?: 10007