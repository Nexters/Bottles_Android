package com.team.bottles.feat.pingpong

import PingPongNavigator
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.feat.pingpong.mvi.PingPongCard
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect
import com.team.bottles.feat.pingpong.mvi.PingPongTab
import com.team.bottles.feat.pingpong.mvi.PingPongUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PingPongViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<PingPongUiState, PingPongSideEffect, PingPongIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): PingPongUiState {
        val test = savedStateHandle.toRoute<PingPongNavigator>()
        Log.d("보틀 아이디", test.bottleId.toString())
        return PingPongUiState.examplePingPongState()
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
            is PingPongIntent.ClickSendLetter -> sendLetter(order = intent.order, text = intent.text)
            is PingPongIntent.OnFocusedTextField -> changeTextFieldState(order = intent.order, isFocused = intent.isFocused)
            is PingPongIntent.OnLetterTextChange -> changeLetterText(order = intent.order, text = intent.text)
            is PingPongIntent.ClickLetterCard -> expandHandleLetterCard(order = intent.order)
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

    private fun sendLetter(order: Int, text: String) {
        launch {
            // TODO : 편지 보내기 UseCase 구현시 연결

            // 1. 현재 PingPongCard.Letter의 상태를 업데이트 함 - myAnswer, shouldAnswer, isDone
            val updatedLetterCard = currentState.pingPongCards.map { card ->
                if (card is PingPongCard.Letter && card.letter.order == order) {
                    card.copy(letter = card.letter.copy(
                            myAnswer = text,
                            shouldAnswer = false,
                            isDone = card.letter.otherAnswer.isNotEmpty()))
                } else {
                    card
                }
            }

            // - 현재 order의 LetterCard의 isDone 값 확인
            val isCurrentLetterDone = updatedLetterCard.filterIsInstance<PingPongCard.Letter>()
                .first { it.letter.order == order }.letter.isDone

            // - 모든 LetterCard가 완료된 상태인지 확인
            val isAllLettersDone = updatedLetterCard.filterIsInstance<PingPongCard.Letter>()
                .all { it.letter.isDone }

            val finalUpdatedCards = updatedLetterCard.map { card ->
                when {
                    isAllLettersDone && card is PingPongCard.Photo -> { // 모든 LetterCard가 완료된 상태라면 PhotoCard를 활성화
                        card.copy(isEnabled = true, isExpanded = true)
                    }
                    isCurrentLetterDone && card is PingPongCard.Letter && card.letter.order == order + 1 -> { // 현재 LetterCard가 완료된 상태라면 다음 LetterCard 를 활성화
                        card.copy(
                            letter = card.letter.copy(
                                canShow = true
                            )
                        )
                    }
                    else -> card
                }
            }

            reduce { copy(pingPongCards = finalUpdatedCards) }
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

        reduce {
            copy(
                pingPongCards = updatedTextFieldState
            )
        }
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

        reduce {
            copy(pingPongCards = updatedPingPongCards)
        }
    }

    private fun expandHandleLetterCard(order: Int) {
        val updatedPingPongCard = currentState.pingPongCards.map { card ->
            when {
                card is PingPongCard.Letter && card.letter.order == order -> {
                    card.copy(
                        isExpanded = !card.isExpanded
                    )
                }
                else -> card
            }
        }

        reduce {
            copy(
                pingPongCards = updatedPingPongCard
            )
        }
    }

}