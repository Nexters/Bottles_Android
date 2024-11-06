package com.team.bottles.feat.profile.introduction.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.feat.profile.BuildConfig
import java.io.File

@Stable
data class IntroductionUiState(
    val token: Token = Token()
) : UiState {

    val url: String
        get() = BuildConfig.BOTTLES_INTRODUCTION_URL +
                "?accessToken=${token.accessToken}" +
                "&refreshToken=${token.refreshToken}" +
                "&device=${BuildConfig.DEVICE}" +
                "&version=${BuildConfig.APP_VERSION}"

}
