package com.team.bottles.core.designsystem.foundation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

internal enum class BottlesShapeDefaults(val shape: Shape) {
    RADIUS_XS(shape = RoundedCornerShape(8.dp)),
    RADIUS_S(shape = RoundedCornerShape(12.dp)),
    RADIUS_M(shape = RoundedCornerShape(16.dp)),
    RADIUS_L(shape = RoundedCornerShape(20.dp)),
    RADIUS_XL(shape = RoundedCornerShape(24.dp)),
    ;
}