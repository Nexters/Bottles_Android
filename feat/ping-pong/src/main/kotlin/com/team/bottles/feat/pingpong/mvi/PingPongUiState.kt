package com.team.bottles.feat.pingpong.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.TabItem
import com.team.bottles.core.ui.model.UserKeyPoint

@Stable
data class PingPongUiState(
    val showDialog: Boolean = false,
    val isStoppedPingPong: Boolean = false,
    val deleteAfterDay: Int = 0,
    val stopUserName: String = "",
    val currentTab: PingPongTab = PingPongTab.INTRODUCTION,
    val partnerProfile: UserProfile = UserProfile(),
    val partnerLetter: String = "",
    val partnerKeyPoints: List<UserKeyPoint> = emptyList(),
    val kakaoId: String = "",
    val matchStatus: MatchStatus = MatchStatus.IN_CONVERSATION,
    val isFinalAnswer: Boolean = false
) : UiState {

    val isVisibilityBottomBar: Boolean
        get() = currentTab == PingPongTab.MATCHING && matchStatus != MatchStatus.IN_CONVERSATION

    val isMatched: Boolean
        get() = matchStatus == MatchStatus.MATCH_SUCCEEDED

    val title: String
        get() = when (matchStatus) {
            MatchStatus.IN_CONVERSATION -> "${partnerProfile.userName}님의\n결정을 기다리고 있어요"
            MatchStatus.MATCH_SUCCEEDED -> "축하해요! 지금부터 찐-하게\n서로를 알아가 보세요"
            MatchStatus.MATCH_FAILED -> "다른 보틀을\n열어보는 건 어때요?"
        }

    val subTitle: String
        get() = when (matchStatus) {
            MatchStatus.IN_CONVERSATION -> "조금만 더 기다려봐요!"
            MatchStatus.MATCH_SUCCEEDED -> "아이디를 복사해 더 깊은 대화를 나눠보세요"
            MatchStatus.MATCH_FAILED -> "아쉽지만 매칭에 실패했어요"
        }

    val illustration: Int?
        get() = when (matchStatus) {
            MatchStatus.IN_CONVERSATION -> R.drawable.illustration_phone
            MatchStatus.MATCH_SUCCEEDED -> null
            MatchStatus.MATCH_FAILED -> R.drawable.illustration_search_bottle
        }

}

enum class PingPongTab(override val tabName: String): TabItem {
    INTRODUCTION("소개"),
    PING_PONG("가치관 문답"),
    MATCHING("매칭"),
    ;
}

enum class MatchStatus {
    IN_CONVERSATION,
    MATCH_SUCCEEDED,
    MATCH_FAILED,
    ;
}

data class QuestionCard(
    val isExpanded: Boolean = false, // 기본 상태는 카드가 접혀있는 상태
    val canShow: Boolean = false, // 볼수 없으면 enabled의 값을 false로 설정
    val question: String = "", // 질문은 볼수 있으면
    val otherAnswer: Boolean,
    val myAnswer: String = "",
)
