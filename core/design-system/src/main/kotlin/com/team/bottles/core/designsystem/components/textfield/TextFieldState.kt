package com.team.bottles.core.designsystem.components.textfield

sealed class BottlesTextFieldState {

    data object Enabled: BottlesTextFieldState()

    data object Active: BottlesTextFieldState()

    data object Focused: BottlesTextFieldState()

    data object Error: BottlesTextFieldState()

    data object Disabled: BottlesTextFieldState()

}