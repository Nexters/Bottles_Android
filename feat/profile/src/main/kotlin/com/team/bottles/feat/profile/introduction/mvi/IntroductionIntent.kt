package com.team.bottles.feat.profile.introduction.mvi

import com.team.bottles.core.common.UiIntent
import java.io.File

sealed interface IntroductionIntent : UiIntent {

    data object ClickWebCompleteButton : IntroductionIntent

    data object ClickWebCloseButton : IntroductionIntent

    data class ShowToastMessage(val message: String) : IntroductionIntent

}