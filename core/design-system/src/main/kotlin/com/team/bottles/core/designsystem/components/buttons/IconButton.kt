package com.team.bottles.core.designsystem.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(
                horizontal = BottlesTheme.spacing.small,
                vertical = 7.5f.dp
            )
            .noRippleClickable(
                onClick = onClick,
                enabled = enabled
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

@Composable
fun BottlesIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Box(
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
            .padding(10.dp)
            .noRippleClickable(
                onClick = onClick,
                enabled = enabled
            )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = BottlesTheme.color.icon.primary
        )
    }
}

@Preview
@Composable
private fun BottlesIconButtonPreview() {
    BottlesTheme {
        Column {
            BottlesIconButton(
                icon = R.drawable.ic_close_16,
                onClick = {}
            )
            BottlesIconButtonWithText(
                text = "text",
                icon = R.drawable.ic_group_14,
                onClick = {}
            )
        }
    }
}