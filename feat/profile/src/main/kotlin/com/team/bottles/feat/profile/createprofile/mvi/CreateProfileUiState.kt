package com.team.bottles.feat.profile.createprofile.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.feat.profile.BuildConfig

data class CreateProfileUiState(
    val token: Token = Token()
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_CREATE_PROFILE_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}