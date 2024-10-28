package com.team.bottles.feat.pingpong

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesErrorScreen
import com.team.bottles.core.ui.BottlesLoadingScreen
import com.team.bottles.feat.pingpong.components.EmptyBottles
import com.team.bottles.core.ui.model.Bottle
import com.team.bottles.feat.pingpong.components.BottleList
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongUiState
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun PingPongScreen(
    innerPadding: PaddingValues,
    uiState: PingPongUiState,
    onIntent: (PingPongIntent) -> Unit
) {
    when(uiState) {
        is PingPongUiState.Error -> {
            BottlesErrorScreen(
                onClickBackButton = { },
                onClickRetryButton = { onIntent(PingPongIntent.ClickRetryButton) }
            )
        }
        is PingPongUiState.Loading -> {
            BottlesLoadingScreen()
        }
        is PingPongUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BottlesTheme.color.background.primary)
                    .padding(bottom = innerPadding.calculateBottomPadding())
                    .systemBarsPadding()
            ) {
                BottlesTopBar()

                if (uiState.bottles.isEmpty()) {
                    EmptyBottles(onClickGoToSandBeach = { onIntent(PingPongIntent.ClickGoToSandBeach) })
                } else {
                    BottleList(
                        bottles = uiState.bottles.toImmutableList(),
                        onClickItem = { bottle ->
                            onIntent(PingPongIntent.ClickBottleItem(bottle = bottle))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PingPongScreenPreview() {
    BottlesTheme {
        PingPongScreen(
            innerPadding = PaddingValues(),
            uiState = PingPongUiState.Success(
                bottles = Bottle.exampleBottleList().toImmutableList()
            ),
            onIntent = {}
        )
    }
}