package com.team.bottles.core.designsystem.foundation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

internal enum class BottlesPaddingDefaults(val paddingValues: PaddingValues) {
    PADDING_XS(paddingValues = PaddingValues(8.dp)),
    PADDING_S(paddingValues = PaddingValues(12.dp)),
    PADDING_M(paddingValues = PaddingValues(16.dp)),
    PADDING_XL(paddingValues = PaddingValues(24.dp)),
    ;
}