package com.team.bottles.feat.bottle.bottlebox.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.ui.model.Bottle

data class BottleBoxUiState(
    val topTab: BottleBoxTab = BottleBoxTab.TALKING,
    val talkingBottles: List<Bottle> = emptyList(),
    val completeBottles: List<Bottle> = emptyList()
) : UiState {

    enum class BottleBoxTab(val tabName: String) {
        TALKING(tabName = "대화중"),
        COMPLETE(tabName = "완료")
    }

}
