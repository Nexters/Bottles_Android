package com.team.bottles.feat.profile.introduction.mvi

import com.team.bottles.core.common.UiIntent
import java.io.File

sealed interface IntroductionIntent : UiIntent {

    data object ClickBackButton : IntroductionIntent

    data object ClickBottomButton : IntroductionIntent

    data object OnFocusedTextField : IntroductionIntent

    data class ChangeIntroduction(val text: String) : IntroductionIntent

    data class ClickPhoto(val file: File) : IntroductionIntent

    data object ClickDeleteButton : IntroductionIntent

}