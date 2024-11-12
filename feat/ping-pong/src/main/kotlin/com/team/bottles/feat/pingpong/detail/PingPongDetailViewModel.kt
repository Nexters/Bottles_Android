package com.team.bottles.feat.pingpong.detail

import PingPongDetailNavigator
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCase
import com.team.bottles.core.domain.bottle.usecase.ReadPingPongDetailUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongShareKakaoIdUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongSharePhotoUseCase
import com.team.bottles.core.domain.bottle.usecase.SendPingPongLetterUseCase
import com.team.bottles.core.domain.bottle.usecase.StopPingPongUseCase
import com.team.bottles.feat.pingpong.detail.mvi.PingPongCard
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailIntent
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailSideEffect
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailUiState
import com.team.bottles.feat.pingpong.detail.mvi.PingPongTab
import com.team.bottles.feat.pingpong.detail.mvi.ShareSelectButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PingPongDetailViewModel @Inject constructor(
    private val getPingPongDetailUseCase: GetPingPongDetailUseCase,
    private val sendPingPongLetterUseCase: SendPingPongLetterUseCase,
    private val selectPingPongSharePhotoUseCase: SelectPingPongSharePhotoUseCase,
    private val selectPingPongShareKakaoIdUseCase: SelectPingPongShareKakaoIdUseCase,
    private val stopPingPongUseCase: StopPingPongUseCase,
    private val readPingPongDetailUseCase: ReadPingPongDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PingPongDetailUiState, PingPongDetailSideEffect, PingPongDetailIntent>(savedStateHandle) {

    init {
        launch { readPingPongDetailUseCase(bottleId = currentState.bottleId) }
        getPingPongDetail()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): PingPongDetailUiState {
        val bottleId = savedStateHandle.toRoute<PingPongDetailNavigator>().bottleId.toInt()
        return PingPongDetailUiState(bottleId = bottleId)
    }

    override suspend fun handleIntent(intent: PingPongDetailIntent) {
        when (intent) {
            is PingPongDetailIntent.ClickBackButton -> navigateToPingPong()
            is PingPongDetailIntent.ClickReportButton -> navigateToReport()
            is PingPongDetailIntent.ClickTabButton -> changeTab(tab = intent.tab)
            is PingPongDetailIntent.ClickConversationFinishButton -> reduce { copy(showDialog = true) }
            is PingPongDetailIntent.ClickCloseAlert -> reduce { copy(showDialog = false) }
            is PingPongDetailIntent.ClickConfirmAlert -> stopPingPong()
            is PingPongDetailIntent.ClickOtherOpenBottleButton -> navigateToPingPong()
            is PingPongDetailIntent.ClickGoToKakaoTalkButton -> openKakaoTalkApp()
            is PingPongDetailIntent.ClickSendLetter -> sendLetter(order = intent.order, answer = intent.text)
            is PingPongDetailIntent.OnFocusedTextField -> changeTextFieldState(order = intent.order, isFocused = intent.isFocused)
            is PingPongDetailIntent.OnLetterTextChange -> changeLetterText(order = intent.order, text = intent.text)
            is PingPongDetailIntent.ClickLetterCard -> expandHandleLetterCard(order = intent.order)
            is PingPongDetailIntent.ClickPhotoCard -> expandHandlePhotoCard()
            is PingPongDetailIntent.ClickKakaoShareCard -> expandHandleKakaoShareCard()
            is PingPongDetailIntent.ClickLikeSharePhotoButton -> selectLikeSharePhotoButton()
            is PingPongDetailIntent.ClickHateSharePhotoButton -> selectHateSharePhotoButton()
            is PingPongDetailIntent.ClickShareProfilePhoto -> shareProfilePhoto(willShare = intent.willShare)
            is PingPongDetailIntent.ClickShareKakaoId -> shareKakaoId(willMatch = intent.willMatch)
            is PingPongDetailIntent.ClickHateShareKakaoIdButton -> selectHateShareKakaoIdButton()
            is PingPongDetailIntent.ClickLikeShareKakaoIdButton -> selectLikeShareKakaoIdButton()
            is PingPongDetailIntent.ChangeRefreshState -> reduce { copy(isRefreshing = true) }
            is PingPongDetailIntent.RefreshPingPongDetail -> getPingPongDetail()
        }
    }

    private fun getPingPongDetail() {
        launch {
            val result = getPingPongDetailUseCase(bottleId = currentState.bottleId)
            val pingPongCards = result.letters.map { letter ->
                PingPongCard.Letter(letter = letter)
            } + listOf(
                PingPongCard.Photo(pingPongPhotos = result.photos, pingPongPhotoStatus = result.pingPongPhotoStatus),
                PingPongCard.KakaoShare(isFirstSelect = result.matchResult.isFirstSelect)
            )

            reduce {
                copy(
                    isLoading = false,
                    isRefreshing = false,
                    isStoppedPingPong = result.isStopped,
                    deleteAfterDay = result.deleteAfterDays.toInt(),
                    stopUserName = result.stopUserName,
                    partnerProfile = result.userProfile,
                    partnerKakaoId = result.matchResult.otherContact,
                    pingPongMatchStatus = result.pingPongMatchStatus,
                    pingPongCards = pingPongCards,
                )
            }
        }
    }

    override fun handleClientException(throwable: Throwable) { }

    private fun navigateToReport() {
        postSideEffect(
            PingPongDetailSideEffect.NavigateToReport(
            userId = currentState.partnerProfile.userId,
            userName = currentState.partnerProfile.userName,
            userAge = currentState.partnerProfile.age,
            userImageUrl = currentState.partnerProfile.imageUrl
        ))
    }

    private fun openKakaoTalkApp() {
        postSideEffect(PingPongDetailSideEffect.OpenKakaoTalkApp)
    }

    private fun navigateToPingPong() {
        postSideEffect(PingPongDetailSideEffect.NavigateToPingPong)
    }

    private fun changeTab(tab: PingPongTab) {
        reduce { copy(currentTab = tab) }
    }

    private fun stopPingPong() {
        launch {
            stopPingPongUseCase(bottleId = currentState.bottleId)
            reduce { copy(showDialog = false) }
            postSideEffect(PingPongDetailSideEffect.NavigateToPingPong)
        }
    }

    private fun sendLetter(order: Int, answer: String) {
        launch {
            sendPingPongLetterUseCase(bottleId = currentState.bottleId, letterOrder = order, answer = answer)
            getPingPongDetail()
        }
    }

    private fun changeTextFieldState(order: Int, isFocused: Boolean) {
        val updatedTextFieldState = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Letter && card.letter.order == order -> {
                    card.copy(
                        textFiledState = if (isFocused) {
                            BottlesTextFieldState.Focused
                        } else {
                            if (card.text.isEmpty()) {
                                BottlesTextFieldState.Enabled
                            } else {
                                BottlesTextFieldState.Active
                            }
                        }
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedTextFieldState) }
    }

    private fun changeLetterText(order: Int, text: String) {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Letter && card.letter.order == order -> {
                    card.copy(
                        text = if (text.length >= card.maxLength) {
                            text.take(card.maxLength)
                        } else {
                            text
                        },
                        textFiledState = if (text.isEmpty()) BottlesTextFieldState.Focused
                        else BottlesTextFieldState.Active
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun expandHandleLetterCard(order: Int) {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Letter && card.letter.order == order -> {
                    card.copy(
                        isExpanded = !card.isExpanded
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun expandHandlePhotoCard() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Photo -> {
                    card.copy(
                        isExpanded = !card.isExpanded
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun expandHandleKakaoShareCard() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.KakaoShare -> {
                    card.copy(
                        isExpanded = !card.isExpanded
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun selectLikeSharePhotoButton() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Photo -> {
                    card.copy(
                        shareSelectButtonState = ShareSelectButtonState.LIKE_SHARE
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun selectHateSharePhotoButton() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Photo -> {
                    card.copy(
                        shareSelectButtonState = ShareSelectButtonState.HATE_SHARE
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun shareProfilePhoto(willShare: Boolean) {
        launch {
            selectPingPongSharePhotoUseCase(bottleId = currentState.bottleId, willShare = willShare)
            getPingPongDetail()
        }
    }

    private fun shareKakaoId(willMatch: Boolean) {
        launch {
            selectPingPongShareKakaoIdUseCase(bottleId = currentState.bottleId, willMatch = willMatch)
            getPingPongDetail()
        }
    }

    private fun selectLikeShareKakaoIdButton() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.KakaoShare -> {
                    card.copy(
                        shareSelectButtonState = ShareSelectButtonState.LIKE_SHARE
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

    private fun selectHateShareKakaoIdButton() {
        val updatedPingPongCards = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.KakaoShare -> {
                    card.copy(
                        shareSelectButtonState = ShareSelectButtonState.HATE_SHARE
                    )
                }
                else -> card
            }
        }

        reduce { copy(pingPongCards = updatedPingPongCards) }
    }

}