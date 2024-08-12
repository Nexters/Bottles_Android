package com.team.bottles.feat.profile.createprofile.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token

data class CreateProfileUiState(
    val token: Token = Token()
) : UiState