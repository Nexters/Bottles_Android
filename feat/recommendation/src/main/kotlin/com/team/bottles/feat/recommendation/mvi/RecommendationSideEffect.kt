package com.team.bottles.feat.recommendation.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface RecommendationSideEffect : UiSideEffect {

    data object NavigateToSandBeach : RecommendationSideEffect

    data class NavigateToRecommendationDetail(val href: String) : RecommendationSideEffect

}