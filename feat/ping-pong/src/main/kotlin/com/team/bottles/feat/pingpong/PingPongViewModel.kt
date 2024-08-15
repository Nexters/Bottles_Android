package com.team.bottles.feat.pingpong

import PingPongNavigator
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.usecase.GetPingPongDetailUseCase
import com.team.bottles.feat.pingpong.mvi.PingPongCard
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect
import com.team.bottles.feat.pingpong.mvi.PingPongTab
import com.team.bottles.feat.pingpong.mvi.PingPongUiState
import com.team.bottles.feat.pingpong.mvi.ShareSelectButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PingPongViewModel @Inject constructor(
    private val getPingPongDetailUseCase: GetPingPongDetailUseCase,
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
            is PingPongIntent.ClickReportButton -> { /* TODO : userId와 함께 신고하기 화면으로 이동 */ }
            is PingPongIntent.ClickTabButton -> changeTab(tab = intent.tab)
            is PingPongIntent.ClickConversationFinishButton -> reduce { copy(showDialog = true) }
            is PingPongIntent.ClickCloseAlert -> reduce { copy(showDialog = false) }
            is PingPongIntent.ClickConfirmAlert -> deletePingPong()
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
            is PingPongIntent.ClickShareKakaoId -> shareShareKakaoId(willMatch = intent.willMatch)
            is PingPongIntent.ClickHateShareKakaoIdButton -> selectHateShareKakaoIdButton()
            is PingPongIntent.ClickLikeShareKakaoIdButton -> selectLikeShareKakaoIdButton()
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

    private fun openKakaoTalkApp() {
        postSideEffect(PingPongSideEffect.OpenKakaoTalkApp)
    }

    private fun navigateToBottleBox() {
        postSideEffect(PingPongSideEffect.NavigateToBottleBox)
    }

    private fun changeTab(tab: PingPongTab) {
        reduce { copy(currentTab = tab) }
    }

    private fun deletePingPong() {
        launch {
            // TODO : 대화 중단 API 호출
            reduce { copy(showDialog = false) }
            postSideEffect(PingPongSideEffect.NavigateToBottleBox)
        }
    }

    private fun sendLetter(order: Int, answer: String) {
        launch {
            // TODO : 편지 보내기 UseCase 구현시 연결

            // TODO : 핑퐁 정보 가져오기 UseCase 구현시 연결
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
            // TODO : 프로필 사진 공유 UseCase 구현

            // TODO : 핑퐁 정보 가져오기 UseCase 구현시 연결
        }
    }

    private fun shareShareKakaoId(willMatch: Boolean) {
        launch {
            // TODO : 최종 선택 UseCase 구현

            // TODO : 핑퐁 정보 가져오기 UseCase 구현시 연결
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