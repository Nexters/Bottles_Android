package com.team.bottles.feat.bottle.bottlebox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import com.team.bottles.feat.bottle.bottlebox.components.BottleBoxContents
import com.team.bottles.core.ui.BottlesErrorScreen
import com.team.bottles.core.ui.model.Bottle
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxIntent
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun BottleBoxScreen(
    innerPadding: PaddingValues,
    uiState: BottleBoxUiState,
    onIntent: (BottleBoxIntent) -> Unit
) {
    if (uiState.isError) {
        BottlesErrorScreen(
            onClickBackButton = { },
            onClickRetryButton = { onIntent(BottleBoxIntent.ClickRetryButton) }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .systemBarsPadding()
        ) {
            BottlesTopBar()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BottlesTheme.padding.medium),
                horizontalArrangement = Arrangement.spacedBy(
                    space = BottlesTheme.spacing.extraSmall
                )
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

            when (uiState.topTab) {
                BottleBoxUiState.BottleBoxTab.TALKING -> {
                    BottleBoxContents(
                        bottles = uiState.talkingBottles.toImmutableList(),
                        emptyText = stringResource(id = R.string.empty_bottle_box),
                        emptyImage = R.drawable.illustration_basket,
                        onClickItem = { bottle ->
                            onIntent(BottleBoxIntent.ClickBottleItem(bottle = bottle))
                        }
                    )
                }

                BottleBoxUiState.BottleBoxTab.COMPLETE -> {
                    BottleBoxContents(
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
}

@Preview(showBackground = true)
@Composable
private fun BottleBoxScreenPreview() {
    BottlesTheme {
        BottleBoxScreen(
            innerPadding = PaddingValues(),
            uiState = BottleBoxUiState(
                topTab = BottleBoxUiState.BottleBoxTab.TALKING,
                talkingBottles = Bottle.exampleBottleBox().toImmutableList()
            ),
            onIntent = {}
        )
    }
}