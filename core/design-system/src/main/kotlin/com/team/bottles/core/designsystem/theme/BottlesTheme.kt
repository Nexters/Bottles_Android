package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalBottlesTypography = compositionLocalOf<BottlesTypography> {
    error("No typography provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesShape = compositionLocalOf<BottlesShape> {
    error("No shape provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesPadding = compositionLocalOf<BottlesPadding> {
    error("No padding provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesColor = compositionLocalOf<BottlesColors> {
    error("No color provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

val LocalBottlesSpacing = compositionLocalOf<BottlesSpacing> {
    error("No spacing provided! Make sure to wrap all usages of Bottles components in BottlesTheme.")
}

@Composable
fun BottlesTheme(
    spacing: BottlesSpacing = BottlesSpacing.defaultSpacing(),
    color: BottlesColors = BottlesColors.defaultColor(),
    padding: BottlesPadding = BottlesPadding.defaultPadding(),
    shape: BottlesShape = BottlesShape.defaultRadius(),
    typography: BottlesTypography = BottlesTypography.defaultTypography(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBottlesTypography provides typography,
        LocalBottlesShape provides shape,
        LocalBottlesPadding provides padding,
        LocalBottlesColor provides color,
        LocalBottlesSpacing provides spacing,
    ) {
        content.invoke()
    }
}

object BottlesTheme {
    val typography: BottlesTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesTypography.current

    val shape: BottlesShape
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesShape.current

    val padding: BottlesPadding
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesPadding.current

    val color: BottlesColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesColor.current

    val spacing: BottlesSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBottlesSpacing.current
}
