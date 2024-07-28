package com.team.bottles.core.designsystem.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.SolidButton
import com.team.bottles.core.designsystem.foundation.wantedSansStd
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesLineTextFieldWithTrailingIcon(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingIcon: @Composable () -> Unit,
    state: BottlesTextFieldState,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val containerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.container.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.container.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.container.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.container.errorPrimary
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.container.disabledPrimary
    }
    val borderColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.border.enabled
        is BottlesTextFieldState.Active -> BottlesTheme.color.border.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.border.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.border.error
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.border.disabled
    }
    val textColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activePrimary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.text.errorSecondary
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.text.disabledSecondary
    }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = wantedSansStd,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            lineHeight = 14.sp * 1.5f,
            color = textColor
        ),
        cursorBrush = SolidColor(
            value = BottlesTheme.color.border.focusedSecondary
        ),
        enabled = enabled,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = containerColor,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (state is BottlesTextFieldState.Enabled || state is BottlesTextFieldState.Disabled) {
                    Text(
                        text = hint,
                        color = textColor,
                        style = BottlesTheme.typography.body
                    )
                }

                innerTextField.invoke()
            }

            if (state is BottlesTextFieldState.Focused || state is BottlesTextFieldState.Error) {
                Spacer(
                    modifier = Modifier.width(
                        width = BottlesTheme.spacing.spacing12
                    )
                )
                trailingIcon.invoke()
            }
        }
    }
}

@Composable
fun BottlesLineTextFieldWithTrailingButton(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    state: BottlesTextFieldState,
    trailingButton: @Composable () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val containerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.container.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.container.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.container.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.container.errorPrimary
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.container.disabledPrimary
    }
    val borderColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.border.enabled
        is BottlesTextFieldState.Active -> BottlesTheme.color.border.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.border.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.border.error
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.border.disabled
    }
    val textColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activePrimary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.text.errorSecondary
        is BottlesTextFieldState.Disabled -> BottlesTheme.color.text.disabledSecondary
    }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = wantedSansStd,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            lineHeight = 14.sp * 1.5f,
            color = textColor
        ),
        cursorBrush = SolidColor(
            value = BottlesTheme.color.border.focusedSecondary
        ),
        enabled = enabled,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = containerColor,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (state is BottlesTextFieldState.Enabled) {
                    Text(
                        text = hint,
                        color = textColor,
                        style = BottlesTheme.typography.body
                    )
                }

                innerTextField.invoke()
            }

            Spacer(Modifier.width(width = BottlesTheme.spacing.spacing12))

            trailingButton.invoke()
        }
    }
}

@Composable
fun BottlesTextFieldErrorCaption(
    text: String
) {
    Text(
        text = text,
        style = BottlesTheme.typography.caption,
        color = BottlesTheme.color.text.errorPrimary
    )
}

/*==============Preview==============*/

private data class TextFieldUiState(
    val firstTextFieldState: BottlesTextFieldState = BottlesTextFieldState.Enabled
)

@Preview(showBackground = true)
@Composable
private fun LineTextFieldWithTrailingIconPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("Bottles") }
        var value2 by remember { mutableStateOf("") }
        val interactionSource = remember { MutableInteractionSource() }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottlesLineTextFieldWithTrailingIcon( // 1. enabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                state = BottlesTextFieldState.Enabled,
                interactionSource = interactionSource
            )
            BottlesLineTextFieldWithTrailingIcon( // 2. active
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                state = BottlesTextFieldState.Active,
                interactionSource = interactionSource
            )
            BottlesLineTextFieldWithTrailingIcon( // 3. focused
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                state = BottlesTextFieldState.Focused,
                interactionSource = interactionSource
            )
            BottlesLineTextFieldWithTrailingIcon( // 4. error
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                state = BottlesTextFieldState.Error("Error Message"),
                interactionSource = interactionSource
            )
            BottlesLineTextFieldWithTrailingIcon( // 5. disabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                state = BottlesTextFieldState.Disabled,
                interactionSource = interactionSource
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LineTextFieldWithTrailingButtonPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("Bottles") }
        var value2 by remember { mutableStateOf("") }
        val interactionSource = remember { MutableInteractionSource() }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottlesLineTextFieldWithTrailingButton( // 1. enabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeholder",
                state = BottlesTextFieldState.Enabled,
                interactionSource = interactionSource,
                trailingButton = {
                    SolidButton(
                        modifier = Modifier
                            .width(53.dp)
                            .height(36.dp),
                        text = "Text",
                        enabled = value2.isNotEmpty(),
                        onClick = {}
                    )
                }
            )
            BottlesLineTextFieldWithTrailingButton( // 2. active
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                state = BottlesTextFieldState.Active,
                interactionSource = interactionSource,
                trailingButton = {
                    SolidButton(
                        modifier = Modifier
                            .width(53.dp)
                            .height(36.dp),
                        text = "Text",
                        enabled = value.isNotEmpty(),
                        onClick = {}
                    )
                }
            )
            BottlesLineTextFieldWithTrailingButton( // 1. focused
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                state = BottlesTextFieldState.Focused,
                interactionSource = interactionSource,
                trailingButton = {
                    SolidButton(
                        modifier = Modifier
                            .width(53.dp)
                            .height(36.dp),
                        text = "Text",
                        enabled = value.isNotEmpty(),
                        onClick = {}
                    )
                }
            )


        }
    }
}