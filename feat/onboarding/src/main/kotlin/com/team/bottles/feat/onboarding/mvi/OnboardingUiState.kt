package com.team.bottles.feat.onboarding.mvi

import com.team.bottles.core.common.UiState

data class OnboardingUiState(
    val currentPage: OnboardingPage = OnboardingPage.ONE,
    val maxPage: Int = OnboardingPage.entries.size + 1,
): UiState

enum class OnboardingPage(val title: String) {
    ONE(title = "천천히 서로를 알아가는 소개팅\n보틀에 오신 것을 환영해요!"),
    TWO(title = "보틀만의 가치관 문답을 통해\n진솔한 모습을 확인할 수 있어요"),
    THREE(title = "확신이 생겼다면\n사진과 연락처를 주고 받아 보세요"),
    FOUR(title = "설레는 여정\n보틀과 함께 시작해 볼까요?"),
}