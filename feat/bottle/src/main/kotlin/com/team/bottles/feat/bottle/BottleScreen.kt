package com.team.bottles.feat.bottle

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
import com.team.bottles.feat.bottle.mvi.BottleIntent
import com.team.bottles.feat.bottle.mvi.BottleUiState

@Composable
internal fun BottleScreen(
    uiState: BottleUiState,
    onIntent: (BottleIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "보틀(ping-pong) 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "뒤로 가기",
            onClick = { onIntent(BottleIntent.ClickBackButton) },
            contentHorizontalPadding = 12.dp
        )
    }
}