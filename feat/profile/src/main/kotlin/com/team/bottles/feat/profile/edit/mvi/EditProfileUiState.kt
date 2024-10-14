package com.team.bottles.feat.profile.edit.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.feat.profile.BuildConfig

data class EditProfileUiState(
    val token: Token = Token()
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_PROFILE_EDIT_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}