package com.team.bottles.feat.sandbeach.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopup

@Composable
internal fun InPingPong() {
    Spacer(modifier = Modifier.height(97.dp))
    BottlesBalloonPopup(text = "보틀을 클릭해 보세요")
}