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

private object BottlesPaddingDefaults {
    val ExtraSmall = PaddingValues(4.dp)
    val Small = PaddingValues(8.dp)
    val Medium = PaddingValues(16.dp)
    val Large = PaddingValues(24.dp)
}

@Immutable
data class BottlesPadding(
    val extraSmall: PaddingValues,
    val small: PaddingValues,
    val medium: PaddingValues,
    val extraLarge: PaddingValues,
) {
    companion object {
        fun defaultPadding(): BottlesPadding = BottlesPadding(
            extraSmall = BottlesPaddingDefaults.ExtraSmall,
            small = BottlesPaddingDefaults.Small,
            medium = BottlesPaddingDefaults.Medium,
            extraLarge = BottlesPaddingDefaults.Large
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShapePreview() {
    BottlesTheme {
        val boxModifier = Modifier
            .size(size = 100.dp)
            .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.small)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ) {
            Box(modifier = boxModifier.padding(BottlesTheme.padding.extraSmall),
                contentAlignment = Alignment.Center
            ) { Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Blue))}
            Box(modifier = boxModifier.padding(BottlesTheme.padding.small),
                contentAlignment = Alignment.Center
            ) { Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Blue))}
            Box(modifier = boxModifier.padding(BottlesTheme.padding.medium),
                contentAlignment = Alignment.Center
            ) { Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Blue))}
            Box(modifier = boxModifier.padding(BottlesTheme.padding.extraLarge),
                contentAlignment = Alignment.Center
            ) { Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(Color.Blue))}
        }
    }
}