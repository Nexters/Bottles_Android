package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.team.bottles.core.designsystem.foundation.BottlesSpacingDefaults

@Immutable
data class BottlesSpacing(
    val spacing32: Dp,
    val spacing24: Dp,
    val spacing20: Dp,
    val spacing16: Dp,
    val spacing12: Dp,
    val spacing8: Dp,
    val spacing4: Dp,
) {
    companion object {
        fun defaultSpacing(): BottlesSpacing = BottlesSpacing(
            spacing32 = BottlesSpacingDefaults.SPACING_XXL.spacing,
            spacing24 = BottlesSpacingDefaults.SPACING_XL.spacing,
            spacing20 = BottlesSpacingDefaults.SPACING_LG.spacing,
            spacing16 = BottlesSpacingDefaults.SPACING_MD.spacing,
            spacing12 = BottlesSpacingDefaults.SPACING_SM.spacing,
            spacing8 = BottlesSpacingDefaults.SPACING_XS.spacing,
            spacing4 = BottlesSpacingDefaults.SPACING_XXS.spacing,
        )
    }
}
