package com.team.bottles.feat.recommendation.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.feat.recommendation.BuildConfig

data class RecommendationUiState(
    val token: Token = Token()
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_RECOMMENDATIONS_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}