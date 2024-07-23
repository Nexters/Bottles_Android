package com.team.bottles.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.foundation.BottlesPaddingDefaults

@Immutable
data class BottlesPadding(
    val padding4: PaddingValues,
    val padding8: PaddingValues,
    val padding16: PaddingValues,
    val padding24: PaddingValues,
) {
    companion object {
        fun defaultPadding(): BottlesPadding = BottlesPadding(
            padding4 = BottlesPaddingDefaults.PADDING_XS.paddingValues,
            padding8 = BottlesPaddingDefaults.PADDING_S.paddingValues,
            padding16 = BottlesPaddingDefaults.PADDING_M.paddingValues,
            padding24 = BottlesPaddingDefaults.PADDING_XL.paddingValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaddingPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ) {
            BottlesPaddingDefaults.entries.forEach { entry ->
                Box(modifier = Modifier
                    .size(size = 100.dp)
                    .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.radius8)
                    .padding(paddingValues = entry.paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Blue))
                }
            }
        }
    }
}