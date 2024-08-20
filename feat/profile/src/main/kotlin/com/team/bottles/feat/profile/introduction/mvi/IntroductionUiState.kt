package com.team.bottles.feat.profile.introduction.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.ui.model.UserKeyPoint
import java.io.File

@Stable
data class IntroductionUiState(
    val step: IntroductionStep = IntroductionStep.INPUT_INTRODUCTION,
    val maxLength: Int = 300,
    val minLength: Int = 50,
    val introduce: String = "",
    val introductionTextFiledState: BottlesTextFieldState = BottlesTextFieldState.Enabled,
    val isEnabledWithBottomButton: Boolean = false,
    val keyPoints: List<UserKeyPoint> = emptyList(),
    val imageFile: File? = null
) : UiState

enum class IntroductionStep(
    val page: Int,
    val title: String,
    val subTitle: String,
    val buttonText: String,
) {
    INPUT_INTRODUCTION(
        page = 1,
        title = "보틀에 담을\n소개를 작성해 주세요",
        subTitle = "수정이 어려우니 신중하게 작성해 주세요",
        buttonText = "다음"
    ),
    SELECT_USER_IMAGE(
        page = 2,
        title = "보틀에 담을 나의\n사진을 골라주세요",
        subTitle = "가치관 문답을 마친 후 동의 하에 공개돼요",
        buttonText = "완료"
    ),
    ;
}