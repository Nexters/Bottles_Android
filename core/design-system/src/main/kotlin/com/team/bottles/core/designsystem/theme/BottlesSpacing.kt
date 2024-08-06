package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.team.bottles.core.designsystem.foundation.BottlesSpacingDefaults

@Immutable
data class BottlesSpacing(
    val doubleExtraLarge: Dp,
    val extraLarge: Dp,
    val large: Dp,
    val medium: Dp,
    val small: Dp,
    val extraSmall: Dp,
    val doubleExtraSmall: Dp,
) {
    companion object {
        fun defaultSpacing(): BottlesSpacing = BottlesSpacing(
            doubleExtraLarge = BottlesSpacingDefaults.SPACING_XXL.spacing,
            extraLarge = BottlesSpacingDefaults.SPACING_XL.spacing,
            large = BottlesSpacingDefaults.SPACING_LG.spacing,
            medium = BottlesSpacingDefaults.SPACING_MD.spacing,
            small = BottlesSpacingDefaults.SPACING_SM.spacing,
            extraSmall = BottlesSpacingDefaults.SPACING_XS.spacing,
            doubleExtraSmall = BottlesSpacingDefaults.SPACING_XXS.spacing,
        )
    }
}
