package com.team.bottles.feat.bottle.bottlebox

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCase
import com.team.bottles.core.ui.model.Bottle
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxIntent
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxSideEffect
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottleBoxViewModel @Inject constructor(
    private val getPingPongListUseCase: GetPingPongListUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BottleBoxUiState, BottleBoxSideEffect, BottleBoxIntent>(
    savedStateHandle
) {

    init {
        getPingPongList()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): BottleBoxUiState =
        BottleBoxUiState()

    override suspend fun handleIntent(intent: BottleBoxIntent) {
        when (intent) {
            is BottleBoxIntent.ClickBottleItem -> navigateToBottle(bottle = intent.bottle)
            is BottleBoxIntent.ClickTopTab -> changeTab(tab = intent.tab)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToBottle(bottle: Bottle) {
        postSideEffect(BottleBoxSideEffect.NavigateToBottle(bottleId = bottle.id))
    }

    private fun changeTab(tab: BottleBoxUiState.BottleBoxTab) {
        reduce { copy(topTab = tab) }
        getPingPongList()
    }

    private fun getPingPongList() {
        launch {
            getPingPongListUseCase().run {
                reduce {
                    copy(
                        talkingBottles = activeBottles.map { pingPongBottle ->
                            Bottle(
                                id = pingPongBottle.id,
                                imageUrl = pingPongBottle.userImageUrl,
                                name = pingPongBottle.userName,
                                age = pingPongBottle.age,
                                mbti = pingPongBottle.mbti,
                                personality = pingPongBottle.keyword,
                                isRead = pingPongBottle.isRead,
                            )
                        },
                        completeBottles = doneBottles.map { pingPongBottle ->
                            Bottle(
                                id = pingPongBottle.id,
                                imageUrl = pingPongBottle.userImageUrl,
                                name = pingPongBottle.userName,
                                age = pingPongBottle.age,
                                mbti = pingPongBottle.mbti,
                                personality = pingPongBottle.keyword,
                                isRead = pingPongBottle.isRead,
                            )
                        }
                    )
                }
            }
        }
    }

}