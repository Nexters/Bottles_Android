package com.team.bottles.core.designsystem.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesOutLinedButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonType
import com.team.bottles.core.designsystem.components.etc.BottlesToggleButton
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesSettingItemWithButton(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    onClickButton: () -> Unit,
    buttonText: String,
) {
    SettingItem(
        modifier = modifier,
        leadingTitle = {
            SettingItemTitleAndSubTitle(title = title, subTitle = subTitle)
        },
        trailingComponent = {
            BottlesOutLinedButton(
                text = buttonText,
                buttonType = OutlinedButtonType.SM,
                onClick = onClickButton,
                contentHorizontalPadding = BottlesTheme.spacing.small
            )
        }
    )
}

@Composable
fun BottlesSettingItemWithToggleButton(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    SettingItem(
        modifier = modifier,
        leadingTitle = {
            if (subTitle == null) {
                SettingItemSingleTitle(title = title)
            } else {
                SettingItemTitleAndSubTitle(title = title, subTitle = subTitle)
            }
        },
        trailingComponent = {
            BottlesToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    )
}

@Composable
fun BottlesSettingItemWithArrow(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    onClickItem: () -> Unit
) {
    SettingItem(
        modifier = modifier.noRippleClickable(onClick = onClickItem),
        leadingTitle = {
            if (subTitle == null) {
                SettingItemSingleTitle(title = title)
            } else {
                SettingItemTitleAndSubTitle(title = title, subTitle = subTitle)
            }
        },
        trailingComponent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_right_16),
                contentDescription = null,
                tint = BottlesTheme.color.icon.primary
            )
        }
    )
}

@Composable
fun BottlesSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    onClickItem: () -> Unit
) {
    SettingItem(
        modifier = modifier.noRippleClickable(onClick = onClickItem),
        leadingTitle = {
            SettingItemTitleAndSubTitle(title = title, subTitle = subTitle)
        },
    )
}

@Composable
private fun RowScope.SettingItemSingleTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier
            .weight(weight = 1f)
            .height(height = 26.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            style = BottlesTheme.typography.subTitle2,
            color = BottlesTheme.color.text.secondary
        )
    }
}

@Composable
private fun RowScope.SettingItemTitleAndSubTitle(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {
    Column(
        modifier = modifier
            .weight(weight = 1f),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraSmall
        )
    ) {
        Text(
            text = title,
            style = BottlesTheme.typography.subTitle2,
            color = BottlesTheme.color.text.secondary
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subTitle,
            style = BottlesTheme.typography.caption,
            color = BottlesTheme.color.text.tertiary
        )
    }
}

@Composable
private fun SettingItem(
    modifier: Modifier = Modifier,
    leadingTitle: @Composable (RowScope.() -> Unit)? = null,
    trailingComponent: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingTitle != null) {
            leadingTitle()
        }

        if (leadingTitle != null && trailingComponent != null) {
            Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.extraSmall))
        }

        if (trailingComponent != null) {
            trailingComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottlesListTitleAndArrowPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            BottlesSettingItemWithArrow(
                title = "text",
                onClickItem = {}
            )
            BottlesSettingItemWithArrow(
                title = "text",
                subTitle = "subText",
                onClickItem = {}
            )
            BottlesSettingItemWithToggleButton(
                title = "text",
                checked = true,
                onCheckedChange = {}
            )
            BottlesSettingItemWithToggleButton(
                title = "text",
                subTitle = "subText",
                checked = true,
                onCheckedChange = {}
            )
            BottlesSettingItemWithButton(
                title = "text",
                subTitle = "subText",
                onClickButton = { /*TODO*/ },
                buttonText = "Text"
            )
            BottlesSettingItem(
                title = "text",
                subTitle = "subText",
                onClickItem = {}
            )
        }
    }
}