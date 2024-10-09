package com.team.bottles.feat.like.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token

data class LikeUiState(
    val token: Token = Token()
) : UiState