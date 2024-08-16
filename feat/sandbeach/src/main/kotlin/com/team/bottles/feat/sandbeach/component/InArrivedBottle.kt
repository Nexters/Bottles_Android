package com.team.bottles.feat.sandbeach.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.etc.chips.BottlesChip
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopup

@Composable
internal fun InArrivedBottle(bottleValue: Int) {
    Spacer(modifier = Modifier.height(97.dp))
    Box {
        BottlesBalloonPopup(text = "보틀을 클릭해 보세요")
        BottlesChip(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-12).dp,),
            number = bottleValue
        )
    }
}