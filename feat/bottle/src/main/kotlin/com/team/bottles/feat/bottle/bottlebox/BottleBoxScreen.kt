package com.team.bottles.feat.bottle.bottlebox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.buttons.BottlesOutLinedButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonState
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottleContents
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxIntent
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun BottleBoxScreen(
    uiState: BottleBoxUiState,
    onIntent: (BottleBoxIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BottlesTopBar()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BottlesTheme.padding.medium),
            horizontalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraSmall)
            ) {
            BottleBoxUiState.BottleBoxTab.entries.forEach { tab ->
                BottlesOutLinedButton(
                    text = tab.tabName,
                    buttonType = OutlinedButtonType.SM,
                    onClick = { onIntent(BottleBoxIntent.ClickTopTab(tab = tab)) },
                    contentHorizontalPadding = BottlesTheme.spacing.small,
                    state = if (tab == uiState.topTab) OutlinedButtonState.SELECTED
                    else OutlinedButtonState.ENABLED
                )
            }
        }

        when(uiState.topTab) {
            BottleBoxUiState.BottleBoxTab.TALKING -> {
                BottleContents(
                    bottles = uiState.talkingBottles.toImmutableList(),
                    emptyText = stringResource(id = R.string.empty_bottle_box),
                    emptyImage = R.drawable.illustration_basket,
                    onClickItem = { bottle ->
                        onIntent(BottleBoxIntent.ClickBottleItem(bottle = bottle))
                    }
                )
            }
            BottleBoxUiState.BottleBoxTab.COMPLETE -> {
                BottleContents(
                    bottles = uiState.completeBottles.toImmutableList(),
                    emptyText = stringResource(id = R.string.empty_bottle_box),
                    emptyImage = R.drawable.illustration_basket,
                    onClickItem = { bottle ->
                        onIntent(BottleBoxIntent.ClickBottleItem(bottle = bottle))
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottleBoxScreenPreview() {
    BottlesTheme {
        BottleBoxScreen(
            uiState = BottleBoxUiState(),
            onIntent = {}
        )
    }
}