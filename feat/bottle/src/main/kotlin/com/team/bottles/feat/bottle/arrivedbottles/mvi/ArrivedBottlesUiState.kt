package com.team.bottles.feat.bottle.arrivedbottles.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.feat.bottle.BuildConfig

data class ArrivedBottlesUiState(
    val token: Token = Token()
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_ARRIVED_BOTTLES_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}