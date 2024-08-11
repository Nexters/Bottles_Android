package com.team.bottles.feat.pingpong

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.feat.pingpong.components.IntroductionContents
import com.team.bottles.feat.pingpong.components.PingPongTopBar
import com.team.bottles.feat.pingpong.mvi.IntroductionTabState
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongRelationShip
import com.team.bottles.feat.pingpong.mvi.PingPongTab
import com.team.bottles.feat.pingpong.mvi.PingPongUiState

@Composable
internal fun PingPongScreen(
    uiState: PingPongUiState,
    onIntent: (PingPongIntent) -> Unit
) {
    val introductionTabScrollState = rememberLazyListState()
    val pingPongTabScrollState = rememberLazyListState()
    val matchingScrollState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BottlesTheme.color.background.primary,
        topBar = {
            PingPongTopBar(
                onClickLeadingIcon = { onIntent(PingPongIntent.ClickBackButton) },
                onClickTrailingIcon = { onIntent(PingPongIntent.ClickReportButton) },
                onClickTab = { tab -> onIntent(PingPongIntent.ClickTabButton(tab = tab)) },
                userName = uiState.partnerProfile.userName,
                isMatched = uiState.isMatched,
                currentTab = uiState.currentTab
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = when (uiState.currentTab) {
                PingPongTab.INTRODUCTION -> introductionTabScrollState
                PingPongTab.PING_PONG -> pingPongTabScrollState
                PingPongTab.MATCHING -> matchingScrollState
            },
            contentPadding = PaddingValues(
                horizontal = BottlesTheme.spacing.medium
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                when (uiState.currentTab) {
                    PingPongTab.INTRODUCTION -> {
                        IntroductionContents(
                            currentRelationShip = uiState.currentRelationShip,
                            introductionTabState = uiState.introduction,
                            partnerProfile = uiState.partnerProfile,
                            closedDay = uiState.closedDay,
                            onClickConversationFinish = { onIntent(PingPongIntent.ClickConversationFinishButton) }
                        )
                    }

                    PingPongTab.PING_PONG -> {

                    }

                    PingPongTab.MATCHING -> {

                    }
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
            uiState = PingPongUiState(
                currentRelationShip = PingPongRelationShip.ING,
                partnerProfile = UserProfile.sampleUserProfile(),
                introduction = IntroductionTabState(
                    partnerLetter = "편지 내용입니다.",
                    userKeyPoints = UserKeyPoint.exampleUerKeyPoints()
                ),
                isMatched = false
            ),
            onIntent = {}
        )
    }
}