package com.team.bottles.feat.bottle.bottlebox

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
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxIntent
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxUiState

@Composable
internal fun BottleBoxScreen(
    uiState: BottleBoxUiState,
    onIntent: (BottleBoxIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "보틀 보관함 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "보틀 아이템",
            onClick = { onIntent(BottleBoxIntent.ClickBottleItem) },
            contentHorizontalPadding = 12.dp
        )
    }
}