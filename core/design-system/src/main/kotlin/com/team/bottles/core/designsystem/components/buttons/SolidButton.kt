package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.modifier.debounceClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

enum class SolidButtonType(val contentVerticalPadding: Dp) {
    XS(contentVerticalPadding = 7.5.dp),
    SM(contentVerticalPadding = 17.5.dp),
    MD(contentVerticalPadding = 17.5.dp),
    LG(contentVerticalPadding = 21.5.dp),
    ;
}

@Composable
fun BottlesSolidButton(
    modifier: Modifier = Modifier,
    buttonType: SolidButtonType,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentHorizontalPadding: Dp = 0.dp,
    isDebounce: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val shape = when(buttonType) {
        SolidButtonType.XS -> BottlesTheme.shape.extraSmall
        else -> BottlesTheme.shape.small
    }

    SolidButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        isDebounce = isDebounce,
        interactionSource = interactionSource,
        isPressed = isPressed,
        contentPadding = PaddingValues(
            horizontal = contentHorizontalPadding,
            vertical = buttonType.contentVerticalPadding
        ),
    ) {
        val textColor = when {
            !enabled -> BottlesTheme.color.text.disabledPrimary
            isPressed -> BottlesTheme.color.text.pressed
            else -> BottlesTheme.color.text.enabledPrimary
        }

        val textStyle = when(buttonType) {
            SolidButtonType.XS -> BottlesTheme.typography.body
            else -> BottlesTheme.typography.subTitle1
        }

        Text(
            text = text,
            color = textColor,
            style = textStyle
        )
    }
}

@Composable
fun SolidButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    shape: Shape,
    contentPadding: PaddingValues,
    isDebounce: Boolean,
    interactionSource: MutableInteractionSource,
    isPressed: Boolean,
    content: @Composable () -> Unit,
) {
    val backgroundColor = when {
        !enabled -> BottlesTheme.color.container.disabledSecondary
        isPressed -> BottlesTheme.color.container.pressed
        else -> BottlesTheme.color.container.enabledSecondary
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clip(shape = shape)
            .padding(contentPadding)
            .then(
                if (isDebounce) {
                    Modifier.debounceClickable(
                        enabled = enabled,
                        onClick = onClick,
                        interactionSource = interactionSource,
                        indication = null
                    )
                } else {
                    Modifier.clickable(
                        enabled = enabled,
                        onClick = onClick,
                        interactionSource = interactionSource,
                        indication = null
                    )
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }
}

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun SolidButtonPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesSolidButton(
                buttonType = SolidButtonType.XS,
                text = "Text",
                onClick = { },
                contentHorizontalPadding = 12.dp
            )
            BottlesSolidButton(
                buttonType = SolidButtonType.SM,
                text = "Text",
                onClick = { },
                contentHorizontalPadding = 62.5f.dp
            )
            BottlesSolidButton(
                buttonType = SolidButtonType.MD,
                text = "Text",
                onClick = { },
                contentHorizontalPadding = 115.5f.dp
            )
            BottlesSolidButton(
                buttonType = SolidButtonType.LG,
                text = "Text",
                onClick = { },
                contentHorizontalPadding = 147.5f.dp
            )
        }
    }
}