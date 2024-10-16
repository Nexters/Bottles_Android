package com.team.bottles.feat.pingpong

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCase
import com.team.bottles.core.ui.model.Bottle
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
import com.team.bottles.feat.pingpong.mvi.PingPongIntent
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect
import com.team.bottles.feat.pingpong.mvi.PingPongUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PingPongViewModel @Inject constructor(
    private val getPingPongListUseCase: GetPingPongListUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PingPongUiState, PingPongSideEffect, PingPongIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): PingPongUiState =
        PingPongUiState()

    override fun handleClientException(throwable: Throwable) {
        when (throwable) {
            is BottlesException -> showErrorMessage(throwable.message?: "")
            is BottlesNetworkException -> {
                showErrorMessage(throwable.message?: "")
                showErrorScreen()
            }
            else -> showErrorScreen()
        }
    }

    override suspend fun handleIntent(intent: PingPongIntent) {
        when (intent) {
            is PingPongIntent.ClickBottleItem -> navigateToBottle(bottle = intent.bottle)
            is PingPongIntent.ClickRetryButton -> retry()
            is PingPongIntent.ClickGoToSandBeach -> navigateToSandBeach()
        }
    }

    private fun showErrorMessage(message: String) {
        postSideEffect(PingPongSideEffect.ShowErrorMessage(message = message))
    }

    private fun showErrorScreen() {
        reduce { copy(isError = true) }
    }

    private fun retry() {
        initPingPongList()
    }

    private fun navigateToBottle(bottle: Bottle) {
        postSideEffect(PingPongSideEffect.NavigateToPingPongDetail(bottleId = bottle.id))
    }

    private fun navigateToSandBeach() {
        postSideEffect(PingPongSideEffect.NavigateToSandBeach)
    }

    fun initPingPongList() {
        launch {
            val pingPongBottles = getPingPongListUseCase().map { pingPongBottle ->
                Bottle(
                    id = pingPongBottle.id,
                    imageUrl = pingPongBottle.userImageUrl,
                    name = pingPongBottle.userName,
                    age = pingPongBottle.age,
                    mbti = pingPongBottle.mbti,
                    personality = pingPongBottle.keyword,
                    isRead = pingPongBottle.isRead,
                    lastActivatedAt = pingPongBottle.lastActivatedAt,
                    lastStatus = pingPongBottle.lastStatus
                )
            }

            reduce { copy(bottles = pingPongBottles) }
        }
    }

}
