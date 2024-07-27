package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.team.bottles.core.designsystem.modifier.clickableSingleNoRipple
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = BottlesTheme.shape.radius12,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
) {
    val isEnabled: Boolean = state != OutlinedButtonState.DISABLED
    val backgroundColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.container.enabledPrimary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.container.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.container.disabledPrimary
    }
    val borderColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.border.enabled
        OutlinedButtonState.SELECTED -> BottlesTheme.color.border.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.border.disabled
    }
    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    Box(
        modifier = modifier
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickableSingleNoRipple(
                enabled = isEnabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = textColor
        )
    }
}

@Composable
fun OutlinedButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    shape: Shape = BottlesTheme.shape.radius12,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
) {
    val isEnabled: Boolean = state != OutlinedButtonState.DISABLED
    val backgroundColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.container.enabledPrimary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.container.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.container.disabledPrimary
    }
    val borderColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.border.enabled
        OutlinedButtonState.SELECTED -> BottlesTheme.color.border.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.border.disabled
    }
    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    Row(
        modifier = modifier
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickableSingleNoRipple(
                enabled = isEnabled,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        icon.invoke()
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = textColor
        )
    }
}

@Composable
fun OutlinedButtonWithImage(
    modifier: Modifier = Modifier,
    text: String,
    image: Any?,
    onClick: () -> Unit,
    shape: Shape = BottlesTheme.shape.radius12,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
    contentDescription: String? = null,
    placeholder: Painter? = null
) {
    val isEnabled: Boolean = state != OutlinedButtonState.DISABLED
    val backgroundColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.container.enabledPrimary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.container.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.container.disabledPrimary
    }
    val borderColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.border.enabled
        OutlinedButtonState.SELECTED -> BottlesTheme.color.border.selected
        OutlinedButtonState.DISABLED -> BottlesTheme.color.border.disabled
    }
    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    Column(
        modifier = modifier
            .width(142.dp)
            .height(180.dp)
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .clickableSingleNoRipple(
                enabled = isEnabled,
                onClick = onClick
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = image,
            contentDescription = contentDescription,
            placeholder = placeholder,
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = textColor
        )
    }
}
