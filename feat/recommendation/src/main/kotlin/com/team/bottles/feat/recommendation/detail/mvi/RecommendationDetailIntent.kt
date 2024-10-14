package com.team.bottles.feat.recommendation.detail.mvi

import com.team.bottles.core.common.UiIntent

sealed interface RecommendationDetailIntent : UiIntent {

    data object ClickWebCloseButton : RecommendationDetailIntent

    data class ClickLeaveOrAcceptButton(val toastMessage: String) : RecommendationDetailIntent

}