package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalTypography = compositionLocalOf<BottlesTypography> {
val LocalBottlesTypography = compositionLocalOf<BottlesTypography> {
    error("No typography provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesShape = compositionLocalOf<BottlesShape> {
    error("No shape provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesPadding = compositionLocalOf<BottlesPadding> {
    error("No padding provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

@Composable
fun BottlesTheme(
    padding: BottlesPadding = BottlesPadding.defaultPadding(),
    shape: BottlesShape = BottlesShape.defaultRadius(),
    typography: BottlesTypography = BottlesTypography.defaultTypography(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography
        LocalBottlesShape provides shape,
        LocalBottlesPadding provides padding
    ) {
        content.invoke()
    }
}

object BottlesTheme {
    val typography: BottlesTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shape: BottlesShape
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesShape.current

    val padding: BottlesPadding
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesPadding.current
}