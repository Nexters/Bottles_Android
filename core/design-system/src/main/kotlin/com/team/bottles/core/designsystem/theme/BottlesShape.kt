package com.team.bottles.core.designsystem.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.foundation.BottlesShapeDefaults

@Immutable
data class BottlesShape(
    val extraSmall: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val extraLarge: Shape,
) {
    companion object {
        fun defaultRadius(): BottlesShape = BottlesShape (
            extraSmall = BottlesShapeDefaults.RADIUS_XS.shape,
            small = BottlesShapeDefaults.RADIUS_S.shape,
            medium = BottlesShapeDefaults.RADIUS_M.shape,
            large = BottlesShapeDefaults.RADIUS_L.shape,
            extraLarge = BottlesShapeDefaults.RADIUS_XL.shape,
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
            BottlesShapeDefaults.entries.forEach { entry ->
                Box(modifier = Modifier
                    .size(size = 100.dp)
                    .border(width = 1.dp, color = Color.Black, shape = entry.shape)
                )
            }
        }
    }
}