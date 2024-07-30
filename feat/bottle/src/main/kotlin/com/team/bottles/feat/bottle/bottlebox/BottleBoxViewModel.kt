package com.team.bottles.feat.bottle.bottlebox

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxIntent
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxSideEffect
import com.team.bottles.feat.bottle.bottlebox.mvi.BottleBoxUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottleBoxViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<BottleBoxUiState, BottleBoxSideEffect, BottleBoxIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BottleBoxUiState =
        BottleBoxUiState()


    override suspend fun handleIntent(intent: BottleBoxIntent) {
        when (intent) {
            is BottleBoxIntent.ClickBottleItem -> navigateToBottle()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToBottle() {
        postSideEffect(BottleBoxSideEffect.NavigateToBottle)
    }

}