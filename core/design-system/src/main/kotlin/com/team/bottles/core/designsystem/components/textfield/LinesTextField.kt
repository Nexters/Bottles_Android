package com.team.bottles.core.designsystem.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme

/**
 * @GunHyung : 디자인 시스템에 정의 되어 있지 않은 색상은 Color.Transparent 로 정의
 */

@Composable
fun BottlesLinesMaxLengthTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    maxLength: Int,
    state: BottlesTextFieldState,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val containerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.container.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.container.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.container.focusedSecondary
        is BottlesTextFieldState.Error -> BottlesTheme.color.container.errorSecondary
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val onContainerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.onContainer.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.onContainer.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.onContainer.focused
        is BottlesTextFieldState.Error -> BottlesTheme.color.onContainer.error
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val borderColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.border.enabled
        is BottlesTextFieldState.Active -> BottlesTheme.color.border.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.border.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.border.error
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val textColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activePrimary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedPrimary
        is BottlesTextFieldState.Error -> BottlesTheme.color.text.errorSecondary
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val countTextColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activeSecondary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedSecondary
        is BottlesTextFieldState.Error -> BottlesTheme.color.text.errorTertiary
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = BottlesTheme.typography.body.copy(
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
        Box(
            modifier = Modifier
                .background(
                    color = containerColor,
                    shape = BottlesTheme.shape.extraLarge
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = BottlesTheme.shape.extraLarge
                )
                .padding(paddingValues = BottlesTheme.padding.medium),
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = onContainerColor,
                        shape = BottlesTheme.shape.small
                    )
                    .padding(paddingValues = BottlesTheme.padding.medium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(203.dp)
                ) {
                    if (state is BottlesTextFieldState.Enabled) {
                        Text(
                            text = hint,
                            color = textColor,
                            style = BottlesTheme.typography.body
                        )
                    }

                    innerTextField.invoke()
                }

                Spacer(Modifier.height(height = BottlesTheme.spacing.small))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = "${value.length} / $maxLength",
                    style = BottlesTheme.typography.body,
                    color = countTextColor
                )
            }
        }
    }
}

@Composable
fun BottlesLinesTextFieldWithButton(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    hint: String,
    state: BottlesTextFieldState,
    buttonText: String,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val containerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.container.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.container.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.container.focusedSecondary
        is BottlesTextFieldState.Error -> Color.Transparent
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val onContainerColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.onContainer.enabledPrimary
        is BottlesTextFieldState.Active -> BottlesTheme.color.onContainer.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.onContainer.focused
        is BottlesTextFieldState.Error -> Color.Transparent
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val borderColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.border.enabled
        is BottlesTextFieldState.Active -> BottlesTheme.color.border.active
        is BottlesTextFieldState.Focused -> BottlesTheme.color.border.focusedPrimary
        is BottlesTextFieldState.Error -> Color.Transparent
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val textColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activePrimary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedPrimary
        is BottlesTextFieldState.Error -> Color.Transparent
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }
    val countTextColor = when (state) {
        is BottlesTextFieldState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is BottlesTextFieldState.Active -> BottlesTheme.color.text.activeSecondary
        is BottlesTextFieldState.Focused -> BottlesTheme.color.text.focusedSecondary
        is BottlesTextFieldState.Error -> Color.Transparent
        is BottlesTextFieldState.Disabled -> Color.Transparent
    }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = BottlesTheme.typography.body.copy(
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
        Column(
            modifier = Modifier
                .background(
                    color = containerColor,
                    shape = BottlesTheme.shape.medium
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = BottlesTheme.shape.medium
                )
                .padding(paddingValues = BottlesTheme.padding.medium),
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = onContainerColor,
                        shape = BottlesTheme.shape.small
                    )
                    .padding(paddingValues = BottlesTheme.padding.medium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                ) {
                    if (state is BottlesTextFieldState.Enabled) {
                        Text(
                            text = hint,
                            color = textColor,
                            style = BottlesTheme.typography.body
                        )
                    }

                    innerTextField.invoke()
                }

                Spacer(Modifier.height(height = BottlesTheme.spacing.small))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = "${value.length} / 100",
                    style = BottlesTheme.typography.body,
                    color = countTextColor
                )
            }

            Spacer(Modifier.height(height = BottlesTheme.spacing.small))

            BottlesSolidButton(
                modifier = Modifier.fillMaxWidth(),
                buttonType = SolidButtonType.MD,
                text = buttonText,
                enabled = state != BottlesTextFieldState.Enabled,
                onClick = onClickButton,
            )
        }
    }
}

/*==============Preview==============*/

@Preview(showBackground = true, heightDp = 1200)
@Composable
private fun BottlesLinesTextFieldPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("") }
        var value2 by remember { mutableStateOf("Bottles") }
        val interaction = remember { MutableInteractionSource() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottlesLinesMaxLengthTextField( // 1. enabled
                value = value,
                onValueChange = { value = it },
                hint = "placeHolder",
                maxLength = 150,
                state = BottlesTextFieldState.Enabled,
                interactionSource = interaction
            )
            BottlesLinesMaxLengthTextField( // 2. active
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeHolder",
                maxLength = 150,
                state = BottlesTextFieldState.Active,
                interactionSource = interaction
            )
            BottlesLinesMaxLengthTextField( // 3. focused
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeHolder",
                maxLength = 150,
                state = BottlesTextFieldState.Focused,
                interactionSource = interaction
            )
            BottlesLinesMaxLengthTextField( // 4. error
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeHolder",
                maxLength = 150,
                state = BottlesTextFieldState.Error,
                interactionSource = interaction
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 750)
@Composable
private fun BottlesLinesTextFieldWithButtonPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("") }
        var value2 by remember { mutableStateOf("Bottles") }
        val interaction = remember { MutableInteractionSource() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottlesLinesTextFieldWithButton(
                // 1. enabled
                value = value,
                onValueChange = { value = it },
                onClickButton = {},
                hint = "placeHolder",
                state = BottlesTextFieldState.Enabled,
                interactionSource = interaction,
                buttonText = "Text",
            )
            BottlesLinesTextFieldWithButton( // 2. active
                value = value2,
                onValueChange = { value2 = it },
                onClickButton = {},
                hint = "placeHolder",
                state = BottlesTextFieldState.Active,
                interactionSource = interaction,
                buttonText = "Text"
            )
            BottlesLinesTextFieldWithButton( // 3. focused
                value = value2,
                onValueChange = { value2 = it },
                onClickButton = {},
                hint = "placeHolder",
                state = BottlesTextFieldState.Focused,
                interactionSource = interaction,
                buttonText = "Text"
            )
        }
    }
}
