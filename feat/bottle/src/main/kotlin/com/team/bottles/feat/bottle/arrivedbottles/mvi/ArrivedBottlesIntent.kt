package com.team.bottles.feat.bottle.arrivedbottles.mvi

import com.team.bottles.core.common.UiIntent

sealed interface ArrivedBottlesIntent : UiIntent {

    data object ClickWebCloseButton : ArrivedBottlesIntent

    data object ClickWebBottleAcceptButton : ArrivedBottlesIntent

}