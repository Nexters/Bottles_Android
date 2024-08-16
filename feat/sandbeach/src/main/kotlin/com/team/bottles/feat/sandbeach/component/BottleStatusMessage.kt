package com.team.bottles.feat.sandbeach.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.sandbeach.mvi.BottleStatus

@Composable
internal fun BottleStatusMessage(
    bottleStatus: BottleStatus,
    newBottleValue: Int,
    bottleBoxValue: Int
) {
    Text(
        text = when (bottleStatus) {
            BottleStatus.REQUIRE_INTRODUCTION -> "보틀에 오신 것을\n환영해요!"
            BottleStatus.IN_ARRIVED_BOTTLE -> "${newBottleValue}개의 새로운\n보틀이 도착했어요"
            BottleStatus.IN_BOTTLE_BOX -> "${bottleBoxValue}개의 보틀이\n남아있어요"
            BottleStatus.NONE_BOTTLE -> "아직 보틀을 찾지 못했어요"
        },
        textAlign = TextAlign.Center,
        style = BottlesTheme.typography.title1,
        color = Color.Black // TODO : 디자인 토큰 요소 유무
    )
}