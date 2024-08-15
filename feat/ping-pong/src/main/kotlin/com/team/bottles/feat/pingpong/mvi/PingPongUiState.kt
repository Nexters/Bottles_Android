package com.team.bottles.feat.pingpong.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
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
    val partnerKakaoId: String = "",
    val pingPongMatchStatus: PingPongMatchStatus = PingPongMatchStatus.NONE,
    val pingPongCards: List<PingPongCard> = listOf(
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Letter(),
        PingPongCard.Photo(),
        PingPongCard.KakaoShare(),
    )
) : UiState {

    val partnerKeyPoints: List<UserKeyPoint>
        get() = partnerProfile.profileSelect.run {
            UserKeyPoint.pingPong(
                keyWords = listOf(job, mbti, region.city, height.toString(), smoking, alcohol),
                personality = keyword,
                hobbies = interest.run { etc + sports + entertainment + culture }
            )
        }

    val partnerLetter: String
        get() = partnerProfile.introduction.joinToString(" ") { it.answer }

    val isVisibilityBottomBar: Boolean
        get() = currentTab == PingPongTab.MATCHING &&
                (pingPongMatchStatus == PingPongMatchStatus.MATCH_FAILED || pingPongMatchStatus == PingPongMatchStatus.MATCH_SUCCEEDED)

    val isMatched: Boolean
        get() = pingPongMatchStatus == PingPongMatchStatus.MATCH_SUCCEEDED

    val matchingResult: MatchingResult
        get() = when (pingPongMatchStatus) {
            PingPongMatchStatus.MATCH_SUCCEEDED -> MatchingResult.SUCCESS
            PingPongMatchStatus.MATCH_FAILED -> MatchingResult.FAIL
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