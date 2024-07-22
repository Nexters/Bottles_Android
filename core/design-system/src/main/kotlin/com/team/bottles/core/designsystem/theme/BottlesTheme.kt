package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalTypography = compositionLocalOf<BottlesTypography> {
    error("No typography provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

@Composable
fun BottlesTheme(
    typography: BottlesTypography = BottlesTypography.defaultTypography(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography
    ) {
        content.invoke()
    }
}

object BottlesTheme {
    val typography: BottlesTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}