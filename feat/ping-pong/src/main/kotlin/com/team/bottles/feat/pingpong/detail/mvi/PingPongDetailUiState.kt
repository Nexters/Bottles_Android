package com.team.bottles.feat.pingpong.detail.mvi

import androidx.compose.runtime.Stable
import com.team.bottles.core.common.UiState
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotoStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotos
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.TabItem
import com.team.bottles.core.ui.model.UserKeyPoint

@Stable
data class PingPongDetailUiState(
    val isRefreshing: Boolean = false,
    val bottleId: Int = 0,
    val showDialog: Boolean = false,
    val isStoppedPingPong: Boolean = false,
    val deleteAfterDay: Int = 0,
    val stopUserName: String = "",
    val currentTab: PingPongTab = PingPongTab.INTRODUCTION,
    val partnerProfile: UserProfile = UserProfile(),
    val partnerKakaoId: String = "",
    val pingPongMatchStatus: PingPongMatchStatus = PingPongMatchStatus.NONE,
    val pingPongCards: List<PingPongCard> = emptyList()
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