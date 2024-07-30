package com.team.bottles.core.designsystem.components.etc.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.clickableSingle
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesBottomNavItem(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    isSelected: Boolean,
) {
    val textStyle = when (isSelected) {
        true -> BottlesTheme.typography.subTitle2
        false -> BottlesTheme.typography.body
    }
    val textColor = when (isSelected) {
        true -> BottlesTheme.color.text.selectedSecondary
        false -> BottlesTheme.color.text.enabledQuaternary
    }

    Column(
        modifier = Modifier
            .width(width = 84.dp)
            .clickableSingle(
                enabled = !isSelected,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.spacing8
        ),
    ) {
        Icon(
            modifier = Modifier.size(
                size = BottlesTheme.spacing.spacing32
            ),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified // TODO : 선택시 아이콘 색상 바뀔 수 있음
        )
        Text(
            text = stringResource(id = label),
            style = textStyle,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottlesBottomNavItemPreview() {
    BottlesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesBottomNavItem(
                onClick = {},
                icon = R.drawable.ic_down_24,
                label = R.string.sand_beach,
                isSelected = true
            )
            BottlesBottomNavItem(
                onClick = {},
                icon = R.drawable.ic_down_24,
                label = R.string.sand_beach,
                isSelected = false
            )
        }
    }
}