package com.team.bottles.feat.bottle.mvi

import com.team.bottles.core.common.UiIntent

sealed interface BottleIntent : UiIntent {

    data object ClickBackButton : BottleIntent

}