package com.team.bottles.feat.pingpong.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.model.MatchStatus
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.domain.bottle.model.PingPongMatchResult
import com.team.bottles.core.domain.bottle.model.PingPongPhoto
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
    val isFinalAnswer: Boolean = false,
    val pingPongCards: List<PingPongCard> = listOf(
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Photo(),
        PingPongCard.Final(),
    )
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

    companion object {
        fun examplePingPongState() : PingPongUiState = PingPongUiState(
            pingPongCards = listOf(
                PingPongCard.Letter(
                    letter = PingPongLetter(
                        order = 0,
                        canShow = true,
                        question = "Q. 첫번째 질문, 1 더하기 1이 2인 이유는?",
                        otherAnswer = "답변은 짧게 했는데요?",
                        myAnswer = "vhgvgh",
                        shouldAnswer = false,
                        isDone = true
                    )
                ),
                PingPongCard.Letter(
                    letter = PingPongLetter(
                        canShow = true,
                        order = 1,
                        question = "Q. 두번째 질문, 2 더하기 2가 4인 이유는?",
                        myAnswer = "",
                        otherAnswer = "나는 이미 답변을 했어. 너만 하면돼.",
                        shouldAnswer = true,
                        isDone = false
                    )
                ),
                PingPongCard.Letter(
                    letter = PingPongLetter(
                        canShow = false,
                        order = 2,
                        question = "Q. 세번째 질문, 1 곱하기 1이 1인 이유는?"
                    )
                ),
                PingPongCard.Photo(),
                PingPongCard.Final()
            )
        )
    }

}

enum class PingPongTab(override val tabName: String) : TabItem {
    INTRODUCTION("소개"),
    PING_PONG("가치관 문답"),
    MATCHING("매칭"),
    ;
}

sealed class PingPongCard {

    data class Letter(
        val isExpanded: Boolean = false,
        val letter: PingPongLetter = PingPongLetter(),
        val text: String = "",
        val textFiledState: BottlesTextFieldState = BottlesTextFieldState.Enabled,
        val maxLength: Int = 150
    ) : PingPongCard() {
        val isEnabled: Boolean
            get() = letter.canShow
    }

    data class Photo(
        val isExpanded: Boolean = false,
        val isEnabled: Boolean = false,
        val photo: PingPongPhoto = PingPongPhoto()
    ) : PingPongCard()

    data class Final(
        val isExpanded: Boolean = false,
        val isEnabled: Boolean = false,
        val matchResult: PingPongMatchResult = PingPongMatchResult()
    ) : PingPongCard()

}