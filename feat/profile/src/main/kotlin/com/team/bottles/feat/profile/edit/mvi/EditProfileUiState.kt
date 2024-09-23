package com.team.bottles.feat.profile.edit.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token

data class EditProfileUiState(
    val token: Token = Token()
) : UiState