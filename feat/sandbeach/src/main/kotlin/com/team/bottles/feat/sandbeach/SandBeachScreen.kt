package com.team.bottles.feat.sandbeach

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.feat.sandbeach.mvi.SandBeachIntent
import com.team.bottles.feat.sandbeach.mvi.SandBeachUiState

@Composable
internal fun SandBeachScreen(
    uiState: SandBeachUiState,
    onIntent: (SandBeachIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "모래 사장 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "자기 소개 작성 하기",
            onClick = { onIntent(SandBeachIntent.ClickCreateIntroductionButton) },
            contentHorizontalPadding = 12.dp
        )
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "도착한 보틀함 가기",
            onClick = { onIntent(SandBeachIntent.ClickBottle) },
            contentHorizontalPadding = 12.dp
        )
    }
}