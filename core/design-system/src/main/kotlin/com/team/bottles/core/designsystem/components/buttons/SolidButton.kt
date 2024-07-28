package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.modifier.clickableSingle
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun SolidButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = BottlesTheme.shape.radius12
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = when {
        !enabled -> BottlesTheme.color.container.disabledSecondary
        isPressed -> BottlesTheme.color.container.pressed
        else -> BottlesTheme.color.container.enabledSecondary
    }
    val textColor = when {
        !enabled -> BottlesTheme.color.text.disabledPrimary
        isPressed -> BottlesTheme.color.text.pressed
        else -> BottlesTheme.color.text.enabledPrimary
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clip(shape = shape)
            .clickableSingle(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
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

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun SolidButtonPreview() {
    BottlesTheme {
        val buttonSize = listOf(
            53.dp to 36.dp,
            158.dp to 56.dp,
            264.dp to 56.dp,
            328.dp to 56.dp,
        )
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            buttonSize.forEach { size ->
                SolidButton(
                    modifier = Modifier
                        .width(size.first)
                        .height(size.second),
                    text = "Text",
                    onClick = { }
                )
                SolidButton(
                    modifier = Modifier
                        .width(size.first)
                        .height(size.second),
                    text = "Text",
                    onClick = { },
                    enabled = false
                )
            }
        }
    }
}