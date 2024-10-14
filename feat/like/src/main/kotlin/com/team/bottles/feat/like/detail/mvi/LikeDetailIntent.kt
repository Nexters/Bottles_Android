package com.team.bottles.feat.like.detail.mvi

import com.team.bottles.core.common.UiIntent

sealed interface LikeDetailIntent : UiIntent {

    data object ClickAcceptButton : LikeDetailIntent

    data object ClickWebCloseButton : LikeDetailIntent

}