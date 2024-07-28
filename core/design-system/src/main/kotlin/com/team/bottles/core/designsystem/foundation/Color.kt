package com.team.bottles.core.designsystem.foundation

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

internal val PrimaryPurple500 = Color(0xFF615EFA)
internal val PrimaryPurple400 = Color(0xFF8489FC)
internal val PrimaryPurple300 = Color(0xFFB5B8FF)
internal val PrimaryPurple200 = Color(0xFFD1D3FF)
internal val PrimaryPurple100 = Color(0xFFEAEDFF)

internal val Neutral900 = Color(0xFF202020)
internal val Neutral800 = Color(0xFF414141)
internal val Neutral700 = Color(0xFF5F5F5F)
internal val Neutral600 = Color(0xFF747474)
internal val Neutral500 = Color(0xFF9E9E9E)
internal val Neutral400 = Color(0xFFBCBCBC)
internal val Neutral300 = Color(0xFFE0E0E0)
internal val Neutral200 = Color(0xFFECECEC)
internal val Neutral100 = Color(0xFFF5F5F5)
internal val Neutral50 = Color(0xFFFBFBFB)

internal val Black100 = Color(0xFF000000)
internal val White100 = Color(0xFFFFFFFF)
internal val Red = Color(0xFFF03E3E)

internal val Gradient = Brush.linearGradient(
    0.0f to Neutral50, 1.0f to Neutral50
)

/*===================================================*/

internal object BrandColors {
    internal val brandPrimary = PrimaryPurple400
    internal val brandSecondary = PrimaryPurple500
    internal val brandTertiary = PrimaryPurple300
    internal val brandQuaternary = PrimaryPurple100
}

internal object BackgroundColors {
    internal val bgPrimary = Neutral50
    internal val bgSecondary = White100
    internal val bgTertiary = Gradient
}

internal object ContainerColors {
    internal val containerPrimary = White100
    internal val containerSecondary = PrimaryPurple100
    internal val containerTertiary = Neutral600
    internal val containerEnabledPrimary = White100
    internal val containerEnabledSecondary = PrimaryPurple400
    internal val containerDisabledPrimary = White100
    internal val containerDisabledSecondary = Neutral400
    internal val containerPressed = PrimaryPurple500
    internal val containerSelected = PrimaryPurple100
    internal val containerFocusedPrimary = Neutral100
    internal val containerFocusedSecondary = White100
    internal val containerActive = White100
    internal val containerErrorPrimary = Neutral100
    internal val containerErrorSecondary = White100
}

internal object OnContainerColors {
    internal val onContainerPrimary = PrimaryPurple100
    internal val onContainerSecondary = Neutral100
    internal val onContainerEnabledPrimary = Neutral100
    internal val onContainerEnabledSecondary = White100
    internal val onContainerDisabled = Neutral100
    internal val onContainerFocused = Neutral100
    internal val onContainerActive = Neutral100
    internal val onContainerError = Neutral100
}

internal object TextColors {
    internal val textPrimary = Black100
    internal val textSecondary = Neutral900
    internal val textTertiary = Neutral600
    internal val textQuaternary = White100
    internal val textQuinary = PrimaryPurple500
    internal val textSenary = PrimaryPurple300
    internal val textEnabledPrimary = White100
    internal val textEnabledSecondary = Neutral900
    internal val textEnabledTertiary = Neutral400
    internal val textEnabledQuaternary = Neutral600
    internal val textDisabledPrimary = White100
    internal val textDisabledSecondary = Neutral400
    internal val textPressed = White100
    internal val textSelectedPrimary = PrimaryPurple500
    internal val textSelectedSecondary = Black100
    internal val textFocusedPrimary = Neutral900
    internal val textFocusedSecondary = Neutral600
    internal val textActivePrimary = Neutral900
    internal val textActiveSecondary = Neutral600
    internal val textErrorPrimary = Red
    internal val textErrorSecondary = Neutral900
    internal val textErrorTertiary = Neutral600
}

internal object BorderColors {
    internal val borderPrimary = Neutral300
    internal val borderSecondary = Neutral200
    internal val borderEnabled = Neutral300
    internal val borderDisabled = Neutral300
    internal val borderSelected = PrimaryPurple500
    internal val borderFocusedPrimary = Neutral300
    internal val borderFocusedSecondary = Neutral500
    internal val borderActive = Neutral300
    internal val borderError = Red
}

internal object IconColors {
    internal val iconPrimary = Neutral500
    internal val iconSecondary = Neutral200
    internal val iconDisabled = Neutral200
    internal val iconUpdate = Red
}