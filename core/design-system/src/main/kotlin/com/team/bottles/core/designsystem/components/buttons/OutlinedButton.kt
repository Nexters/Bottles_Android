package com.team.bottles.core.designsystem.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

enum class OutlinedButtonState {
    ENABLED,
    SELECTED,
    DISABLED,
    ;
}

enum class OutlinedButtonType(val contentVerticalPadding: Dp) {
    SM(contentVerticalPadding = 7.5.dp),
    MD(contentVerticalPadding = 17.5.dp),
    LG(contentVerticalPadding = 17.5.dp),
}

@Composable
fun BottlesOutLinedButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonType: OutlinedButtonType,
    onClick: () -> Unit,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
    contentHorizontalPadding: Dp = 0.dp,
) {
    val shape = when (buttonType) {
        OutlinedButtonType.SM -> BottlesTheme.shape.radius8
        else -> BottlesTheme.shape.radius12
    }

    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        state = state,
        contentPadding = PaddingValues(
            vertical = buttonType.contentVerticalPadding,
            horizontal = contentHorizontalPadding
        ),
    ) {
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = textColor
        )
    }
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    shape: Shape,
    onClick: () -> Unit,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
    content: @Composable () -> Unit,
) {
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

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clip(shape = shape)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .padding(contentPadding)
            .clickable(
                enabled = state != OutlinedButtonState.DISABLED,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }
}

@Composable
fun BottlesOutlinedButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
    contentHorizontalPadding: Dp = 0.dp,
) {
    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = BottlesTheme.shape.radius8,
        state = state,
        contentPadding = PaddingValues(
            vertical = OutlinedButtonType.SM.contentVerticalPadding,
            horizontal = contentHorizontalPadding
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.spacing8,
            )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = textColor
            )
        }
    }
}

@Composable
fun BottlesOutlinedButtonWithImage(
    modifier: Modifier = Modifier,
    text: String,
    image: Any?,
    onClick: () -> Unit,
    state: OutlinedButtonState = OutlinedButtonState.ENABLED,
) {
    val textColor = when (state) {
        OutlinedButtonState.ENABLED -> BottlesTheme.color.text.secondary
        OutlinedButtonState.SELECTED -> BottlesTheme.color.text.selectedPrimary
        OutlinedButtonState.DISABLED -> BottlesTheme.color.text.disabledSecondary
    }

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = BottlesTheme.shape.radius8,
        state = state,
        contentPadding = PaddingValues(
            vertical = 23.5f.dp,
            horizontal = 21.dp
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.spacing12,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(
                modifier = Modifier.size(100.dp),
                imageModel = { image },
                previewPlaceholder = painterResource(id = R.drawable.sample_image),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                )
            )
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 800)
@Composable
private fun BottlesOutlinedButtonPreview() {
    val states = listOf(
        OutlinedButtonState.ENABLED,
        OutlinedButtonState.SELECTED,
        OutlinedButtonState.DISABLED
    )

    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            states.forEach { state ->
                BottlesOutLinedButton(
                    text = "text",
                    buttonType = OutlinedButtonType.SM,
                    state = state,
                    contentHorizontalPadding = 12.dp,
                    onClick = { }
                )
            }

            states.forEach { state ->
                BottlesOutlinedButtonWithIcon(
                    text = "text",
                    state = state,
                    icon = R.drawable.ic_group_14,
                    contentHorizontalPadding = 12.dp,
                    onClick = { }
                )
            }

            states.forEach { state ->
                BottlesOutLinedButton(
                    text = "text",
                    buttonType = OutlinedButtonType.MD,
                    state = state,
                    contentHorizontalPadding = 64.5f.dp,
                    onClick = { }
                )
            }

            states.forEach { state ->
                BottlesOutLinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "text",
                    buttonType = OutlinedButtonType.LG,
                    state = state,
                    onClick = { }
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                states.forEach { state ->
                    BottlesOutlinedButtonWithImage(
                        text = "text",
                        image = null,
                        state = state,
                        onClick = { }
                    )
                }
            }
        }
    }
}