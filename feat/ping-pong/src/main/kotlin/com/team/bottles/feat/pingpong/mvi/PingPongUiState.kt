package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiState
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.TabItem
import com.team.bottles.core.ui.model.UserKeyPoint

data class PingPongUiState(
    val currentRelationShip: PingPongRelationShip = PingPongRelationShip.ING,
    val currentTab: PingPongTab = PingPongTab.INTRODUCTION,
    val partnerProfile: UserProfile = UserProfile(),
    val introduction: IntroductionTabState = IntroductionTabState(),
    val isMatched: Boolean = false,
    val closedDay: Int = 0
) : UiState

enum class PingPongTab(override val tabName: String): TabItem {
    INTRODUCTION("소개"),
    PING_PONG("가치관 문답"),
    MATCHING("매칭"),
    ;
}

enum class PingPongRelationShip {
    ING,
    SUCCESS,
    FAIL,
    ;
}

data class IntroductionTabState(
    val partnerLetter: String = "",
    val userKeyPoints: List<UserKeyPoint> = emptyList()
) {
    companion object {
        fun sample(): IntroductionTabState = IntroductionTabState(
            partnerLetter = "편지 내용입니다. 어쩌구 저쩌구",
            userKeyPoints = UserKeyPoint.exampleUerKeyPoints()
        )
    }
}

data class MatchingTabState(
    val kakaoId: String = ""
)

data class QuestionCard(
    val isExpanded: Boolean = false, // 기본 상태는 카드가 접혀있는 상태
    val canShow: Boolean = false, // 볼수 없으면 enabled의 값을 false로 설정
    val question: String = "", // 질문은 볼수 있으면
    val otherAnswer: Boolean,
    val myAnswer: String = "",
)
