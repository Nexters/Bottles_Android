package com.team.bottles.feat.bottle.arrivedbottles

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
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesIntent
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesUiState

@Composable
internal fun ArrivedBottlesScreen(
    uiState: ArrivedBottlesUiState,
    onIntent: (ArrivedBottlesIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "도착한 보틀 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "뒤로 가기",
            onClick = { onIntent(ArrivedBottlesIntent.ClickBackButton) },
            contentHorizontalPadding = 12.dp
        )
    }
}