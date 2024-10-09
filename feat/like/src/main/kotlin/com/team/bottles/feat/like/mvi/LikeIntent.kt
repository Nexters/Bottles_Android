package com.team.bottles.feat.like.mvi

import com.team.bottles.core.common.UiIntent

sealed interface LikeIntent : UiIntent {

    data object ClickBottleItem : LikeIntent

}