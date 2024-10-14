package com.team.bottles.feat.recommendation.detail.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface RecommendationDetailSideEffect : UiSideEffect {

    data object NavigateToRecommendation : RecommendationDetailSideEffect

    data class ShowToastMessage(val message: String) : RecommendationDetailSideEffect

}