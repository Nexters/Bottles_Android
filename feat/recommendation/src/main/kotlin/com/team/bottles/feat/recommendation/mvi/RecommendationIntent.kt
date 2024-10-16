package com.team.bottles.feat.recommendation.mvi

import com.team.bottles.core.common.UiIntent

sealed interface RecommendationIntent : UiIntent {

    data object ClickWebCloseButton : RecommendationIntent

    data class ClickBottleItem(val href: String) : RecommendationIntent

}