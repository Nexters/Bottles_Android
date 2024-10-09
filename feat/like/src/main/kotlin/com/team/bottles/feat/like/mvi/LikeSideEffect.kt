package com.team.bottles.feat.like.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface LikeSideEffect : UiSideEffect {

    data object NavigateToLikeDetail : LikeSideEffect

}