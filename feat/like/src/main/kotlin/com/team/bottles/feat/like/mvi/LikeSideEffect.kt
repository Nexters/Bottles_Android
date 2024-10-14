package com.team.bottles.feat.like.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface LikeSideEffect : UiSideEffect {

    data class NavigateToLikeDetail(val href: String) : LikeSideEffect

}