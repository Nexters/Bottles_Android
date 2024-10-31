package com.team.bottles.feat.pingpong.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.BottlesAlertDialogLeftDismissRightConfirm
import com.team.bottles.core.ui.BottlesLoadingScreen
import com.team.bottles.core.ui.model.AlertType
import com.team.bottles.feat.pingpong.detail.components.PingPongBottomBar
import com.team.bottles.feat.pingpong.detail.components.PingPongTopBar
import com.team.bottles.feat.pingpong.detail.components.introductionContents
import com.team.bottles.feat.pingpong.detail.components.matchingContents
import com.team.bottles.feat.pingpong.detail.components.pingPongContents
import com.team.bottles.feat.pingpong.detail.mvi.PingPongCard
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailIntent
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailUiState
import com.team.bottles.feat.pingpong.detail.mvi.PingPongTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PingPongDetailScreen(
    uiState: PingPongDetailUiState,
    onIntent: (PingPongDetailIntent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val introductionTabScrollState = rememberLazyListState()
    val pingPongTabScrollState = rememberLazyListState()
    val matchingScrollState = rememberLazyListState()
    val pullRefreshState = rememberPullToRefreshState(
        enabled = { uiState.currentTab == PingPongTab.PING_PONG }
    )

    LaunchedEffect(uiState.isRefreshing) {
        if (uiState.isRefreshing) {
            pullRefreshState.startRefresh()
            onIntent(PingPongDetailIntent.RefreshPingPongDetail)
        } else {
            pullRefreshState.endRefresh()
        }
    }

    if (pullRefreshState.isRefreshing && !uiState.isRefreshing) {
        onIntent(PingPongDetailIntent.ChangeRefreshState)
    }

    if (uiState.showDialog) {
        BottlesAlertDialogLeftDismissRightConfirm(
            onClose = { onIntent(PingPongDetailIntent.ClickCloseAlert) },
            onConfirm = { onIntent(PingPongDetailIntent.ClickConfirmAlert) },
            onDismiss = { onIntent(PingPongDetailIntent.ClickCloseAlert) },
            confirmButtonText = AlertType.STOP_PING_PONG.confirmText,
            dismissButtonText = AlertType.STOP_PING_PONG.dismissText,
            title = AlertType.STOP_PING_PONG.title,
            content = AlertType.STOP_PING_PONG.content
        )
    }

    if (uiState.isLoading) {
        BottlesLoadingScreen()
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .systemBarsPadding(),
            containerColor = Color.Transparent,
            topBar = {
                PingPongTopBar(
                    onClickLeadingIcon = { onIntent(PingPongDetailIntent.ClickBackButton) },
                    onClickTrailingIcon = { onIntent(PingPongDetailIntent.ClickReportButton) },
                    onClickTab = { tab -> onIntent(PingPongDetailIntent.ClickTabButton(tab = tab)) },
                    partnerName = uiState.partnerProfile.userName,
                    pingPongMatchStatus = uiState.pingPongMatchStatus,
                    currentTab = uiState.currentTab
                )
            },
            bottomBar = {
                if (uiState.isVisibilityBottomBar) {
                    PingPongBottomBar(
                        onClickButton = {
                            when (uiState.isMatched) {
                                true -> onIntent(PingPongDetailIntent.ClickGoToKakaoTalkButton)
                                false -> onIntent(PingPongDetailIntent.ClickOtherOpenBottleButton)
                            }
                        },
                        isMatched = uiState.isMatched,
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BottlesTheme.color.background.primary)
                    .padding(innerPadding)
                    .nestedScroll(connection = pullRefreshState.nestedScrollConnection)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
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
                            )
                        }

                        PingPongTab.PING_PONG -> {
                            pingPongContents(
                                pingPongCards = uiState.pingPongCards,
                                pingPongMatchStatus = uiState.pingPongMatchStatus,
                                onClickSendLetter = { order, text ->
                                    onIntent(PingPongDetailIntent.ClickSendLetter(order = order, text = text))
                                },
                                onValueChange = { order, text ->
                                    onIntent(
                                        PingPongDetailIntent.OnLetterTextChange(
                                            order = order,
                                            text = text
                                        )
                                    )
                                },
                                onClickLetterCard = { order ->
                                    onIntent(PingPongDetailIntent.ClickLetterCard(order = order))
                                },
                                onFocusedTextField = { order, isFocused ->
                                    onIntent(
                                        PingPongDetailIntent.OnFocusedTextField(
                                            order = order,
                                            isFocused = isFocused
                                        )
                                    )
                                },
                                onClickLikeSharePhoto = { onIntent(PingPongDetailIntent.ClickLikeSharePhotoButton) },
                                onClickHateSharePhoto = { onIntent(PingPongDetailIntent.ClickHateSharePhotoButton) },
                                onClickPhotoCard = { onIntent(PingPongDetailIntent.ClickPhotoCard) },
                                onClickShareProfilePhoto = { willShare ->
                                    onIntent(PingPongDetailIntent.ClickShareProfilePhoto(willShare = willShare))
                                },
                                onClickLikeShareKakaoId = { onIntent(PingPongDetailIntent.ClickLikeShareKakaoIdButton) },
                                onClickHateShareKakaoId = { onIntent(PingPongDetailIntent.ClickHateShareKakaoIdButton) },
                                onClickKakaoShareCard = { onIntent(PingPongDetailIntent.ClickKakaoShareCard) },
                                onClickShareKakaoId = { willMatch ->
                                    onIntent(PingPongDetailIntent.ClickShareKakaoId(willMatch = willMatch))
                                },
                            )
                        }

                        PingPongTab.MATCHING -> {
                            matchingContents(
                                matchingResult = uiState.matchingResult,
                                kakaoId = uiState.partnerKakaoId,
                                partnerName = uiState.partnerProfile.userName
                            )
                        }
                    }

                    if (uiState.currentTab != PingPongTab.MATCHING) {
                        item(key = "Finish PingPong Button") {
                            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

                            Text(
                                modifier = Modifier
                                    .debounceNoRippleClickable(
                                        onClick = { onIntent(PingPongDetailIntent.ClickConversationFinishButton) },
                                        enabled = !uiState.isStoppedPingPong
                                    ),
                                text = stringResource(id = R.string.conversation_finish),
                                style = BottlesTheme.typography.subTitle2,
                                color = if (!uiState.isStoppedPingPong) BottlesTheme.color.text.enabledSecondary
                                else BottlesTheme.color.text.disabledSecondary
                            )
                        }
                    }
                }

                PullToRefreshContainer(
                    modifier = Modifier.align(Alignment.TopCenter),
                    state = pullRefreshState,
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 1000)
@Composable
private fun PingPongScreenPreview() {
    BottlesTheme {
        PingPongDetailScreen(
            uiState = PingPongDetailUiState(
                //showDialog = true,
                currentTab = PingPongTab.INTRODUCTION,
                pingPongMatchStatus = PingPongMatchStatus.NONE,
                partnerProfile = UserProfile.sampleUserProfile(),
                pingPongCards = listOf(
                    PingPongCard.Letter(letter = PingPongLetter(order = 0)),
                    PingPongCard.Letter(letter = PingPongLetter(order = 1)),
                    PingPongCard.Letter(letter = PingPongLetter(order = 2)),
                    PingPongCard.Photo(),
                    PingPongCard.KakaoShare()
                )
            ),
            onIntent = {}
        )
    }
}