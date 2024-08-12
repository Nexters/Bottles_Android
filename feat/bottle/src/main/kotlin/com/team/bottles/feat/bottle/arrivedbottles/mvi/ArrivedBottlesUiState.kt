package com.team.bottles.feat.bottle.arrivedbottles.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.auth.model.Token

data class ArrivedBottlesUiState(
    val token: Token = Token()
) : UiState