package com.team.bottles.core.designsystem.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesIconButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.enabledPrimary,
                shape = BottlesTheme.shape.extraSmall
            )
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.enabled,
                shape = BottlesTheme.shape.extraSmall
            )
            .clip(shape = BottlesTheme.shape.extraSmall)
            .noRippleClickable(
                onClick = onClick,
                enabled = enabled
            )
            .padding(
                horizontal = BottlesTheme.spacing.small,
                vertical = 7.5f.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.doubleExtraSmall,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = BottlesTheme.color.icon.primary
        )
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.enabledSecondary
        )
    }
}

enum class IconButtonType {
    CIRCLE,
    RECTANGLE,
    ;
}

@Composable
fun BottlesIconButton(
    modifier: Modifier = Modifier,
    iconButtonType: IconButtonType,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val shape = when (iconButtonType) {
        IconButtonType.RECTANGLE -> BottlesTheme.shape.extraSmall
        IconButtonType.CIRCLE -> CircleShape
    }
    val padding = when (iconButtonType) {
        IconButtonType.RECTANGLE -> 10.dp
        IconButtonType.CIRCLE -> 6.dp
    }

    Box(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.enabledPrimary,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.enabled,
                shape = shape
            )
            .clip(shape = shape)
            .noRippleClickable(
                onClick = onClick,
                enabled = enabled
            )
            .padding(all = padding)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = BottlesTheme.color.icon.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottlesIconButtonPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesIconButton(
                icon = R.drawable.ic_close_16,
                iconButtonType = IconButtonType.RECTANGLE,
                onClick = {}
            )
            BottlesIconButtonWithText(
                text = "text",
                icon = R.drawable.ic_group_14,
                onClick = {}
            )
            BottlesIconButton(
                icon = R.drawable.ic_pencil_12,
                iconButtonType = IconButtonType.CIRCLE,
                onClick = {}
            )
        }
    }
}