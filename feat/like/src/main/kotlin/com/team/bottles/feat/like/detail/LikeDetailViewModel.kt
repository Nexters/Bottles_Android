package com.team.bottles.feat.like.detail

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.like.detail.mvi.LikeDetailIntent
import com.team.bottles.feat.like.detail.mvi.LikeDetailSideEffect
import com.team.bottles.feat.like.detail.mvi.LikeDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<LikeDetailUiState, LikeDetailSideEffect, LikeDetailIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LikeDetailUiState = LikeDetailUiState

    override suspend fun handleIntent(intent: LikeDetailIntent) {
        when (intent) {
            is LikeDetailIntent.ClickWebCloseButton -> navigateToLikeDetail()
            is LikeDetailIntent.ClickAcceptButton -> navigateToQna()
        }
    }

    override fun handleClientException(throwable: Throwable) {} // TODO : 예외 처리

    private fun navigateToLikeDetail() {
        postSideEffect(LikeDetailSideEffect.NavigateToLikeDetail)
    }

    private fun navigateToQna() {
        postSideEffect(LikeDetailSideEffect.NavigateToQna)
    }

}