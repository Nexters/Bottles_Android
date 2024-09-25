package com.team.bottles.feat.bottle.bottlebox.mvi

import com.team.bottles.core.common.UiIntent
import com.team.bottles.core.ui.model.Bottle

sealed interface BottleBoxIntent : UiIntent {

    data class ClickBottleItem(val bottle: Bottle) : BottleBoxIntent

    data class ClickTopTab(val tab: BottleBoxUiState.BottleBoxTab) : BottleBoxIntent

    data object ClickRetryButton : BottleBoxIntent

}