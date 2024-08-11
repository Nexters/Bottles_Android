package com.team.bottles.feat.pingpong

import PingPongNavigator
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
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
        return PingPongUiState()
    }


    override suspend fun handleIntent(intent: PingPongIntent) {
        when (intent) {
            is PingPongIntent.ClickBackButton -> navigateToBottleBox()
            is PingPongIntent.ClickReportButton -> { /* TODO : 신고기능 */ }
            is PingPongIntent.ClickTabButton -> changeTab(tab = intent.tab)
            is PingPongIntent.ClickConversationFinishButton -> finishPingPong()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToBottleBox() {
        postSideEffect(PingPongSideEffect.NavigateToBottleBox)
    }

    private fun changeTab(tab: PingPongTab) {
        reduce { copy(currentTab = tab) }
    }

    private fun finishPingPong() {
        // TODO : 핑퐁 종료 버튼 누르면 sideEffect로 Alter 띄우기
    }
}