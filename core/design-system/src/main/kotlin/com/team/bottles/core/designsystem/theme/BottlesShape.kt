package com.team.bottles.core.designsystem.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private object BottlesShapeDefaults {
    val ExtraSmall = RoundedCornerShape(8.dp)
    val Small = RoundedCornerShape(12.dp)
    val Medium = RoundedCornerShape(16.dp)
    val ExtraLarge = RoundedCornerShape(24.dp)
}

@Immutable
data class BottlesShape(
    val extraSmall: CornerBasedShape,
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val extraLarge: CornerBasedShape,
) {
    companion object {
        fun defaultRadius(): BottlesShape = BottlesShape (
            extraSmall = BottlesShapeDefaults.ExtraSmall,
            small = BottlesShapeDefaults.Small,
            medium = BottlesShapeDefaults.Medium,
            extraLarge = BottlesShapeDefaults.ExtraLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShapePreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ) {
            Box(modifier = Modifier
                .size(size = 100.dp)
                .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.extraSmall)
            )
            Box(modifier = Modifier
                .size(size = 100.dp)
                .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.small)
            )
            Box(modifier = Modifier
                .size(size = 100.dp)
                .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.medium)
            )
            Box(modifier = Modifier
                .size(size = 100.dp)
                .border(width = 1.dp, color = Color.Black, shape = BottlesTheme.shape.extraLarge)
            )
        }
    }
}