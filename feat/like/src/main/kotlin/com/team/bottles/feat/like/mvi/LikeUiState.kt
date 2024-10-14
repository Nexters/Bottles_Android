package com.team.bottles.feat.like.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.feat.like.BuildConfig

data class LikeUiState(
    val token: Token = Token(),
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_LIKE_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}