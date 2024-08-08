package com.team.bottles.feat.bottle

import BottleNavigator
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesIntent
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesSideEffect
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesUiState
import com.team.bottles.feat.bottle.mvi.BottleIntent
import com.team.bottles.feat.bottle.mvi.BottleSideEffect
import com.team.bottles.feat.bottle.mvi.BottleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<BottleUiState, BottleSideEffect, BottleIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BottleUiState {
        val test = savedStateHandle.toRoute<BottleNavigator.Bottle>()
        Log.d("보틀 아이디", test.bottleId.toString())
        return BottleUiState()
    }


    override suspend fun handleIntent(intent: BottleIntent) {
        when (intent) {
            is BottleIntent.ClickBackButton -> navigateToBottleBox()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToBottleBox() {
        postSideEffect(BottleSideEffect.NavigateToBottleBox)
    }

}