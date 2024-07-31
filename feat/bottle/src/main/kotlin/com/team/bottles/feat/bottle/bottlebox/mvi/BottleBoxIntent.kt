package com.team.bottles.feat.bottle.bottlebox.mvi

import com.team.bottles.core.common.UiIntent

sealed interface BottleBoxIntent : UiIntent {

    data object ClickBottleItem : BottleBoxIntent

}