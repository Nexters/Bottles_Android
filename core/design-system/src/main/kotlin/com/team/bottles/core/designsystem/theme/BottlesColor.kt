package com.team.bottles.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.team.bottles.core.designsystem.foundation.BackgroundColors
import com.team.bottles.core.designsystem.foundation.BorderColors
import com.team.bottles.core.designsystem.foundation.BrandColors
import com.team.bottles.core.designsystem.foundation.ContainerColors
import com.team.bottles.core.designsystem.foundation.IconColors
import com.team.bottles.core.designsystem.foundation.OnContainerColors
import com.team.bottles.core.designsystem.foundation.TextColors

@Immutable
data class BottlesColors(
    val brand: Brand,
    val background: Background,
    val container: Container,
    val onContainer: OnContainer,
    val text: Text,
    val border: Border,
    val icon: Icon
) {
    companion object {
        fun defaultColor(): BottlesColors = BottlesColors(
            brand = Brand(),
            background = Background(),
            container = Container(),
            onContainer = OnContainer(),
            text = Text(),
            border = Border(),
            icon = Icon()
        )
    }
}

@Immutable
data class Brand(
    val primary: Color = BrandColors.brandPrimary,
    val secondary: Color = BrandColors.brandSecondary,
    val tertiary: Color = BrandColors.brandTertiary,
    val quaternary: Color = BrandColors.brandQuaternary
)

@Immutable
data class Background(
    val primary: Color = BackgroundColors.bgPrimary,
    val secondary: Color = BackgroundColors.bgSecondary,
    val tertiary: Brush = BackgroundColors.bgTertiary
)

@Immutable
data class Container(
    val primary: Color = ContainerColors.containerPrimary,
    val secondary: Color = ContainerColors.containerSecondary,
    val tertiary: Color = ContainerColors.containerTertiary,
    val enabledPrimary: Color = ContainerColors.containerEnabledPrimary,
    val enabledSecondary: Color = ContainerColors.containerEnabledSecondary,
    val disabledPrimary: Color = ContainerColors.containerDisabledPrimary,
    val pressed: Color = ContainerColors.containerPressed,
    val selected: Color = ContainerColors.containerSelected,
    val focusedPrimary: Color = ContainerColors.containerFocusedPrimary,
    val focusedSecondary: Color = ContainerColors.containerFocusedSecondary,
    val active: Color = ContainerColors.containerActive,
    val errorPrimary: Color = ContainerColors.containerErrorPrimary,
    val errorSecondary: Color = ContainerColors.containerErrorSecondary
)

@Immutable
data class OnContainer(
    val primary: Color = OnContainerColors.onContainerPrimary,
    val secondary: Color = OnContainerColors.onContainerSecondary,
    val enabledPrimary: Color = OnContainerColors.onContainerEnabledPrimary,
    val enabledSecondary: Color = OnContainerColors.onContainerEnabledSecondary,
    val disabled: Color = OnContainerColors.onContainerDisabled,
    val focused: Color = OnContainerColors.onContainerFocused,
    val active: Color = OnContainerColors.onContainerActive,
    val error: Color = OnContainerColors.onContainerError
)

@Immutable
data class Text(
    val primary: Color = TextColors.textPrimary,
    val secondary: Color = TextColors.textSecondary,
    val tertiary: Color = TextColors.textTertiary,
    val quaternary: Color = TextColors.textQuaternary,
    val quinary: Color = TextColors.textQuinary,
    val senary: Color = TextColors.textSenary,
    val enabledPrimary: Color = TextColors.textEnabledPrimary,
    val enabledSecondary: Color = TextColors.textEnabledSecondary,
    val enabledTertiary: Color = TextColors.textEnabledTertiary,
    val enabledQuaternary: Color = TextColors.textEnabledQuaternary,
    val disabledPrimary: Color = TextColors.textDisabledPrimary,
    val disabledSecondary: Color = TextColors.textDisabledSecondary,
    val pressed: Color = TextColors.textPressed,
    val selectedPrimary: Color = TextColors.textSelectedPrimary,
    val selectedSecondary: Color = TextColors.textSelectedSecondary,
    val focusedPrimary: Color = TextColors.textFocusedPrimary,
    val focusedSecondary: Color = TextColors.textFocusedSecondary,
    val activePrimary: Color = TextColors.textActivePrimary,
    val activeSecondary: Color = TextColors.textActiveSecondary,
    val errorPrimary: Color = TextColors.textErrorPrimary,
    val errorSecondary: Color = TextColors.textErrorSecondary,
    val errorTertiary: Color = TextColors.textErrorTertiary
)

@Immutable
data class Border(
    val primary: Color = BorderColors.borderPrimary,
    val secondary: Color = BorderColors.borderSecondary,
    val enabled: Color = BorderColors.borderEnabled,
    val disabled: Color = BorderColors.borderDisabled,
    val selected: Color = BorderColors.borderSelected,
    val focused: Color = BorderColors.borderFocused,
    val active: Color = BorderColors.borderActive,
    val error: Color = BorderColors.borderError
)

@Immutable
data class Icon(
    val primary: Color = IconColors.iconPrimary,
    val secondary: Color = IconColors.iconSecondary,
    val disabled: Color = IconColors.iconDisabled,
    val update: Color = IconColors.iconUpdate
)
