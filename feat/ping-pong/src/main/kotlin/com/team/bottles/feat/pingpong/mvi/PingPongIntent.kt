package com.team.bottles.feat.pingpong.mvi

import com.team.bottles.core.common.UiIntent

sealed interface PingPongIntent : UiIntent {

    data object ClickBackButton : PingPongIntent

    data object ClickReportButton : PingPongIntent

    data class ClickTabButton(val tab: PingPongTab) : PingPongIntent

    data object ClickConversationFinishButton : PingPongIntent

    data object ClickCloseAlert : PingPongIntent

    data object ClickConfirmAlert : PingPongIntent

    data object ClickGoToKakaoTalkButton : PingPongIntent

    data object ClickOtherOpenBottleButton : PingPongIntent

    data class ClickSendLetter(
        val order: Int,
        val text: String
    ) : PingPongIntent

    data class OnFocusedTextField(
        val order: Int,
        val isFocused: Boolean
    ) : PingPongIntent

    data class OnLetterTextChange(
        val order: Int,
        val text: String
    ) : PingPongIntent

    data class ClickLetterCard(val order: Int) : PingPongIntent

    data object ClickPhotoCard : PingPongIntent

    data object ClickLikeSharePhotoButton : PingPongIntent

    data object ClickHateSharePhotoButton : PingPongIntent

    data class ClickShareProfilePhoto(val willShare: Boolean) : PingPongIntent

    data object ClickKakaoShareCard : PingPongIntent

    data object ClickLikeShareKakaoIdButton : PingPongIntent

    data object ClickHateShareKakaoIdButton : PingPongIntent

    data class ClickShareKakaoId(val willMatch: Boolean) : PingPongIntent

}