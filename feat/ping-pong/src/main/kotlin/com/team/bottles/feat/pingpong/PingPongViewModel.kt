package com.team.bottles.feat.pingpong

import PingPongNavigator
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongShareKakaoIdUseCase
import com.team.bottles.core.domain.bottle.usecase.SelectPingPongSharePhotoUseCase
import com.team.bottles.core.domain.bottle.usecase.SendPingPongLetterUseCase
import com.team.bottles.core.domain.bottle.usecase.StopPingPongUseCase
import com.team.bottles.feat.pingpong.mvi.PingPongCard
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect
import com.team.bottles.feat.pingpong.mvi.PingPongTab
import com.team.bottles.feat.pingpong.mvi.PingPongUiState
import com.team.bottles.feat.pingpong.mvi.ShareSelectButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class PingPongViewModel @Inject constructor(
    private val getPingPongDetailUseCase: GetPingPongDetailUseCase,
    private val sendPingPongLetterUseCase: SendPingPongLetterUseCase,
    private val selectPingPongSharePhotoUseCase: SelectPingPongSharePhotoUseCase,
    private val selectPingPongShareKakaoIdUseCase: SelectPingPongShareKakaoIdUseCase,
    private val stopPingPongUseCase: StopPingPongUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PingPongUiState, PingPongSideEffect, PingPongIntent>(savedStateHandle) {

    init {
        getPingPongDetail()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): PingPongUiState {
        val bottleId = savedStateHandle.toRoute<PingPongNavigator>().bottleId.toInt()
        return PingPongUiState(bottleId = bottleId)
    }

    override suspend fun handleIntent(intent: PingPongIntent) {
        when (intent) {
            is PingPongIntent.ClickBackButton -> navigateToBottleBox()
            is PingPongIntent.ClickReportButton -> navigateToReport()
            is PingPongIntent.ClickTabButton -> changeTab(tab = intent.tab)
            is PingPongIntent.ClickConversationFinishButton -> reduce { copy(showDialog = true) }
            is PingPongIntent.ClickCloseAlert -> reduce { copy(showDialog = false) }
            is PingPongIntent.ClickConfirmAlert -> stopPingPong()
            is PingPongIntent.ClickOtherOpenBottleButton -> navigateToBottleBox()
            is PingPongIntent.ClickGoToKakaoTalkButton -> openKakaoTalkApp()
            is PingPongIntent.ClickSendLetter -> sendLetter(order = intent.order, answer = intent.text)
            is PingPongIntent.OnFocusedTextField -> changeTextFieldState(order = intent.order, isFocused = intent.isFocused)
            is PingPongIntent.OnLetterTextChange -> changeLetterText(order = intent.order, text = intent.text)
            is PingPongIntent.ClickLetterCard -> expandHandleLetterCard(order = intent.order)
            is PingPongIntent.ClickPhotoCard -> expandHandlePhotoCard()
            is PingPongIntent.ClickKakaoShareCard -> expandHandleKakaoShareCard()
            is PingPongIntent.ClickLikeSharePhotoButton -> selectLikeSharePhotoButton()
            is PingPongIntent.ClickHateSharePhotoButton -> selectHateSharePhotoButton()
            is PingPongIntent.ClickShareProfilePhoto -> shareProfilePhoto(willShare = intent.willShare)
            is PingPongIntent.ClickShareKakaoId -> shareKakaoId(willMatch = intent.willMatch)
            is PingPongIntent.ClickHateShareKakaoIdButton -> selectHateShareKakaoIdButton()
            is PingPongIntent.ClickLikeShareKakaoIdButton -> selectLikeShareKakaoIdButton()
            is PingPongIntent.ChangeRefreshState -> reduce { copy(isRefreshing = true) }
            is PingPongIntent.RefreshPingPong -> getPingPongDetail()
        }
    }

    private fun getPingPongDetail() {
        launch {
            delay(300L)
            val result = getPingPongDetailUseCase(bottleId = currentState.bottleId)

            val pingPongCards = result.letters.map { letter ->
                PingPongCard.Letter(letter = letter)
            } + listOf(
                PingPongCard.Photo(pingPongPhotos = result.photos, pingPongPhotoStatus = result.pingPongPhotoStatus),
                PingPongCard.KakaoShare(isFirstSelect = result.matchResult.isFirstSelect)
            )

            reduce {
                copy(
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

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToReport() {
        postSideEffect(PingPongSideEffect.NavigateToReport(
            userId = currentState.partnerProfile.userId,
            userName = currentState.partnerProfile.userName,
            userAge = currentState.partnerProfile.age,
            userImageUrl = currentState.partnerProfile.imageUrl
        ))
    }

    private fun openKakaoTalkApp() {
        postSideEffect(PingPongSideEffect.OpenKakaoTalkApp)
    }

    private fun navigateToBottleBox() {
        postSideEffect(PingPongSideEffect.NavigateToBottleBox)
    }

    private fun changeTab(tab: PingPongTab) {
        reduce { copy(currentTab = tab) }
    }

    private fun stopPingPong() {
        launch {
            stopPingPongUseCase(bottleId = currentState.bottleId)
            reduce { copy(showDialog = false) }
            postSideEffect(PingPongSideEffect.NavigateToBottleBox)
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