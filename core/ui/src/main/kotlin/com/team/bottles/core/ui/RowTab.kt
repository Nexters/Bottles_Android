package com.team.bottles.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.core.designsystem.components.buttons.BottlesOutLinedButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonState
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme

interface TabItem {
    val tabName: String
}

@Composable
fun <T> BottlesRowTab(
    tabs: List<T>,
    stateProvider: (T) -> OutlinedButtonState,
    onIntent: (T) -> Unit,
) where T : TabItem {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BottlesTheme.padding.medium),
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraSmall
        )
    ) {
        tabs.forEach { tab ->
            BottlesOutLinedButton(
                text = tab.tabName,
                buttonType = OutlinedButtonType.SM,
                onClick = { onIntent(tab) },
                contentHorizontalPadding = BottlesTheme.spacing.small,
                state = stateProvider(tab)
            )
        }
    }
}
