package com.team.bottles.feat.pingpong.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.model.MatchStatus
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.domain.bottle.model.PingPongPhotoStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotos
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
    val partnerKakaoId: String = "",
    val matchStatus: MatchStatus = MatchStatus.NONE,
    val isFinalAnswer: Boolean = false,
    val pingPongCards: List<PingPongCard> = listOf(
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Photo(),
        PingPongCard.KakaoShare(),
    )
) : UiState {

    val isVisibilityBottomBar: Boolean
        get() = currentTab == PingPongTab.MATCHING &&
                (matchStatus == MatchStatus.MATCH_FAILED || matchStatus == MatchStatus.MATCH_SUCCEEDED)

    val isMatched: Boolean
        get() = matchStatus == MatchStatus.MATCH_SUCCEEDED

    val matchingResult: MatchingResult
        get() = when (matchStatus) {
            MatchStatus.MATCH_SUCCEEDED -> MatchingResult.SUCCESS
            MatchStatus.MATCH_FAILED -> MatchingResult.FAIL
            else -> MatchingResult.WAITING
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
                PingPongCard.KakaoShare()
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

enum class MatchingResult {
    WAITING,
    SUCCESS,
    FAIL
}

sealed class PingPongCard {

    data class Letter(
        val isExpanded: Boolean = false,
        val letter: PingPongLetter = PingPongLetter(),
        val text: String = "",
        val textFiledState: BottlesTextFieldState = BottlesTextFieldState.Enabled,
        val maxLength: Int = 150
    ) : PingPongCard()

    data class Photo(
        val isExpanded: Boolean = false,
        val shareSelectButtonState: ShareSelectButtonState = ShareSelectButtonState.NONE,
        val pingPongPhotos: PingPongPhotos = PingPongPhotos(),
        val pingPongPhotoStatus: PingPongPhotoStatus = PingPongPhotoStatus.NONE
    ) : PingPongCard()

    data class KakaoShare(
        val isExpanded: Boolean = false,
        val isFirstSelect: Boolean = true,
        val shareSelectButtonState: ShareSelectButtonState = ShareSelectButtonState.NONE,
    ) : PingPongCard()

}

enum class ShareSelectButtonState {
    LIKE_SHARE,
    HATE_SHARE,
    NONE,
    ;
}