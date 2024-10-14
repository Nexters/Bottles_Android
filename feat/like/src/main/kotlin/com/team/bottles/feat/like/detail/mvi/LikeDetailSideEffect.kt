package com.team.bottles.feat.like.detail.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface LikeDetailSideEffect : UiSideEffect {

    data object NavigateToLikeDetail : LikeDetailSideEffect

    data object NavigateToQna : LikeDetailSideEffect

}