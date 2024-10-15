package com.team.bottles.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.core.designsystem.components.etc.menu.BottlesTabItem
import com.team.bottles.core.designsystem.components.etc.menu.BottlesTabItemState
import com.team.bottles.core.designsystem.theme.BottlesTheme

interface TabItem {
    val tabName: String
}

@Composable
fun <T> BottlesRowTab(
    modifier: Modifier = Modifier,
    tabs: List<T>,
    stateProvider: (T) -> BottlesTabItemState,
    onIntent: (T) -> Unit,
) where T : TabItem {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BottlesTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.doubleExtraSmall
        )
    ) {
        tabs.forEach { tab ->
            BottlesTabItem(
                text = tab.tabName,
                state = stateProvider(tab),
                onClickTab = { onIntent(tab) }
            )
        }
    }
}
