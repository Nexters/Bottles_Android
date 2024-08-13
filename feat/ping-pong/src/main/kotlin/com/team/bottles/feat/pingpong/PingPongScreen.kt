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
import com.team.bottles.core.ui.BottlesAlertDialog
import com.team.bottles.core.ui.model.AlertType
import com.team.bottles.feat.pingpong.components.PingPongBottomBar
import com.team.bottles.feat.pingpong.components.PingPongTopBar
import com.team.bottles.feat.pingpong.components.introductionContents
import com.team.bottles.feat.pingpong.components.matchingContents
import com.team.bottles.feat.pingpong.mvi.MatchStatus
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
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

    if (uiState.showDialog) {
        BottlesAlertDialog(
            onClose = { onIntent(PingPongIntent.ClickCloseAlert) },
            onConfirm = { onIntent(PingPongIntent.ClickConfirmAlert) },
            confirmText = AlertType.STOP_PING_PONG.confirmText,
            dismissText = AlertType.STOP_PING_PONG.dismissText,
            title = AlertType.STOP_PING_PONG.title,
            content = AlertType.STOP_PING_PONG.content
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BottlesTheme.color.background.primary,
        topBar = {
            PingPongTopBar(
                onClickLeadingIcon = { onIntent(PingPongIntent.ClickBackButton) },
                onClickTrailingIcon = { onIntent(PingPongIntent.ClickReportButton) },
                onClickTab = { tab -> onIntent(PingPongIntent.ClickTabButton(tab = tab)) },
                userName = uiState.partnerProfile.userName,
                isFinalAnswer = uiState.isFinalAnswer,
                currentTab = uiState.currentTab
            )
        },
        bottomBar = {
            if (uiState.isVisibilityBottomBar) {
                PingPongBottomBar(
                    onClickButton = {
                        when (uiState.isMatched) {
                            true -> onIntent(PingPongIntent.ClickGoToKakaoTalkButton)
                            false -> onIntent(PingPongIntent.ClickOtherOpenBottleButton)
                        }
                    },
                    isMatched = uiState.isMatched,
                )
            }
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
                start = BottlesTheme.spacing.medium,
                end = BottlesTheme.spacing.medium,
                top = BottlesTheme.spacing.doubleExtraLarge,
                bottom = BottlesTheme.spacing.extraLarge
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState.currentTab) {
                PingPongTab.INTRODUCTION -> {
                    introductionContents(
                        isStoppedPingPong = uiState.isStoppedPingPong,
                        partnerProfile = uiState.partnerProfile,
                        partnerLetter = uiState.partnerLetter,
                        partnerKeyPoints = uiState.partnerKeyPoints,
                        deleteAfterDay = uiState.deleteAfterDay,
                        matchStatus = uiState.matchStatus,
                        onClickConversationFinish = { onIntent(PingPongIntent.ClickConversationFinishButton) }
                    )
                }

                PingPongTab.PING_PONG -> {

                }

                PingPongTab.MATCHING -> {
                    matchingContents(
                        matchStatus = uiState.matchStatus,
                        title = uiState.title,
                        subTitle = uiState.subTitle,
                        illustration = uiState.illustration,
                        kakaoId = uiState.kakaoId
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
            uiState = PingPongUiState(
                currentTab = PingPongTab.INTRODUCTION,
                matchStatus = MatchStatus.IN_CONVERSATION,
                isFinalAnswer = true,
                partnerProfile = UserProfile.sampleUserProfile()
            ),
            onIntent = {}
        )
    }
}