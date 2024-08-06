package com.team.bottles.feat.profile.introduction.mvi

import android.net.Uri
import com.team.bottles.core.common.UiIntent

sealed interface IntroductionIntent : UiIntent {

    data object ClickBackButton : IntroductionIntent

    data object ClickBottomButton : IntroductionIntent

    data object OnFocusedTextField : IntroductionIntent

    data class ChangeIntroduction(val text: String) : IntroductionIntent

    data class ClickPhoto(val uri: Uri) : IntroductionIntent

}