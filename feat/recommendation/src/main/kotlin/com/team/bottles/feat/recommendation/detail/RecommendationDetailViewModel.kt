package com.team.bottles.feat.recommendation.detail

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailIntent
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailSideEffect
import com.team.bottles.feat.recommendation.detail.mvi.RecommendationDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RecommendationDetailUiState, RecommendationDetailSideEffect, RecommendationDetailIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendationDetailUiState = RecommendationDetailUiState

    override suspend fun handleIntent(intent: RecommendationDetailIntent) {
        when (intent) {
            is RecommendationDetailIntent.ClickWebCloseButton -> navigateToRecommendation()
            is RecommendationDetailIntent.ClickLeaveOrAcceptButton -> showToastMessage(message = intent.toastMessage)
        }
    }

    override fun handleClientException(throwable: Throwable) { }

    private fun navigateToRecommendation() {
        postSideEffect(RecommendationDetailSideEffect.NavigateToRecommendation)
    }

    private fun showToastMessage(message: String) {
        postSideEffect(RecommendationDetailSideEffect.ShowToastMessage(message = message))
    }

}