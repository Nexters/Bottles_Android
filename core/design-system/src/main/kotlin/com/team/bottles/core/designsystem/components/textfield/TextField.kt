package com.team.bottles.core.designsystem.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
fun TextFieldWithTrailingIcon(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingIcon: @Composable () -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocused: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
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
            color = BottlesTheme.color.text.activePrimary
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
                    color = if (isFocused || isError) BottlesTheme.color.container.focusedPrimary
                    else BottlesTheme.color.container.enabledPrimary,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = if (!isError) BottlesTheme.color.border.enabled
                    else BottlesTheme.color.border.error,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (!isFocused && value.isEmpty()) {
                    Text(
                        text = hint,
                        color = BottlesTheme.color.text.enabledTertiary,
                        style = BottlesTheme.typography.body
                    )
                }

                innerTextField.invoke()
            }

            if (isFocused || isError) {
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
fun TextFieldWithTrailingButton(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingButton: @Composable () -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isFocused: Boolean = interactionSource.collectIsFocusedAsState().value,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
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
            color = BottlesTheme.color.text.activePrimary
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
                    color = if (!isFocused) BottlesTheme.color.container.enabledPrimary
                    else BottlesTheme.color.container.focusedPrimary,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = if (!isError) BottlesTheme.color.border.enabled
                    else BottlesTheme.color.border.error,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (!isFocused && value.isEmpty()) {
                    Text(
                        text = hint,
                        color = BottlesTheme.color.text.enabledTertiary,
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
fun TextFieldErrorCaption(
    text: String
) {
    Text(
        text = text,
        style = BottlesTheme.typography.caption,
        color = BottlesTheme.color.text.errorPrimary
    )
}

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun TextFieldWithTrailingPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("Bottles") }
        var value2 by remember { mutableStateOf("") }
        val isErrorTextField by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            TextFieldWithTrailingIcon( // 1. active
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
            TextFieldWithTrailingIcon( // 2. enabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeholder",
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
            TextFieldWithTrailingIcon( // 3. focused
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                isFocused = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
            Column {
                TextFieldWithTrailingIcon( // 4. error
                    value = value,
                    onValueChange = { value = it },
                    hint = "placeholder",
                    isError = isErrorTextField,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete_24),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                )
                if (isErrorTextField) {
                    Spacer(modifier = Modifier.height(BottlesTheme.spacing.spacing4))
                    TextFieldErrorCaption("error_text")
                }
            }
            TextFieldWithTrailingIcon( // 5. disabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "Disabled",
                enabled = false,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldWithTrailingButtonPreview() {
    BottlesTheme {
        var value by remember { mutableStateOf("Bottles") }
        var value2 by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            TextFieldWithTrailingButton( // 1. active
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
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
            TextFieldWithTrailingButton( // 2. enabled
                value = value2,
                onValueChange = { value2 = it },
                hint = "placeholder",
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
            TextFieldWithTrailingButton( // 3. focused
                value = value,
                onValueChange = { value = it },
                hint = "placeholder",
                isFocused = true,
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