package com.team.bottles.feat.sandbeach.mvi

import com.team.bottles.core.common.UiState

data class SandBeachUiState(
    val bottleStatus: BottleStatus = BottleStatus.NONE_BOTTLE,
    val newBottleValue: Int = 0,
    val bottleBoxValue: Int = 0,
    val afterArrivedTime: Int = 0
): UiState

enum class BottleStatus {
    REQUIRE_INTRODUCTION,
    IN_ARRIVED_BOTTLE,
    IN_BOTTLE_BOX,
    NONE_BOTTLE,
    ;
}

// 자기소개 받아야 하는 상태
// 도착한보틀 O, 보틀 보관함 X - 1순위
// 도착한보틀 x, 보틀 보관함 O - 2순위
// 도착한보틀 x, 보틀 보관함 x - 3순위