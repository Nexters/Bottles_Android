package com.team.bottles.core.designsystem.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithArrow
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesCard(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    contents: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.primary,
                shape = BottlesTheme.shape.extraLarge
            )
            .border(
                width = 1.dp,
                shape = BottlesTheme.shape.extraLarge,
                color = BottlesTheme.color.border.primary
            )
            .clip(shape = BottlesTheme.shape.extraLarge)
            .padding(
                vertical = BottlesTheme.spacing.extraLarge,
                horizontal = BottlesTheme.spacing.medium
            ),
        verticalArrangement = verticalArrangement
    ) {
        contents.invoke()
    }
}

@Preview
@Composable
private fun BottlesCardPreview() {
    BottlesTheme {
        BottlesCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraLarge
            )
        ) {
            BottlesSettingItemWithArrow(
                title = "프로필 수정",
                onClickItem = {}
            )
        }
    }
}