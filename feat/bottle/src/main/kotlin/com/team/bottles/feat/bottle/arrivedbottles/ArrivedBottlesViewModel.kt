package com.team.bottles.feat.bottle.arrivedbottles

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesIntent
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesSideEffect
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArrivedBottlesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<ArrivedBottlesUiState, ArrivedBottlesSideEffect, ArrivedBottlesIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): ArrivedBottlesUiState =
        ArrivedBottlesUiState()


    override suspend fun handleIntent(intent: ArrivedBottlesIntent) {
        when (intent) {
            is ArrivedBottlesIntent.ClickBackButton -> navigateToMain()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToMain() {
        postSideEffect(ArrivedBottlesSideEffect.NavigateToSandBeach)
    }

}