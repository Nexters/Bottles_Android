package com.team.bottles.feat.sandbeach

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.sandbeach.mvi.SandBeachIntent
import com.team.bottles.feat.sandbeach.mvi.SandBeachSideEffect
import com.team.bottles.feat.sandbeach.mvi.SandBeachUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SandBeachViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

): BaseViewModel<SandBeachUiState, SandBeachSideEffect, SandBeachIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SandBeachUiState =
        SandBeachUiState()

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SandBeachIntent) {
        when(intent) {
            is SandBeachIntent.ClickCreateIntroductionButton -> navigateToIntroduction()
            is SandBeachIntent.ClickBottle -> navigateToArrivedBottle()
        }
    }

    private fun navigateToArrivedBottle() {
        postSideEffect(SandBeachSideEffect.NavigateToArrivedBottle)
    }

    private fun navigateToIntroduction() {
        postSideEffect(SandBeachSideEffect.NavigateToIntroduction)
    }

}