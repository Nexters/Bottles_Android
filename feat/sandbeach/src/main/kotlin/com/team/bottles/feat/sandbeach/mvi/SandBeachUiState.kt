package com.team.bottles.feat.sandbeach.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.feat.sandbeach.BuildConfig

data class SandBeachUiState(
    val showDialog: Boolean = false,
    val isError: Boolean = false,
    val bottleStatus: BottleStatus = BottleStatus.NONE_BOTTLE,
    val newBottleValue: Int = 0,
    val bottleBoxValue: Int = 0,
    val afterArrivedTime: Int = 0,
    val appVersionCode: Int = BuildConfig.VERSION_CODE
): UiState

enum class BottleStatus {
    REQUIRE_INTRODUCTION,
    IN_ARRIVED_BOTTLE,
    IN_BOTTLE_BOX,
    NONE_BOTTLE,
    ;
}
