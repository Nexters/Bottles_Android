package com.team.bottles.feat.pingpong.detail.mvi

import com.team.bottles.core.common.UiIntent

sealed interface PingPongDetailIntent : UiIntent {

    data object ClickBackButton : PingPongDetailIntent

    data object ClickReportButton : PingPongDetailIntent

    data class ClickTabButton(val tab: PingPongTab) : PingPongDetailIntent

    data object ClickConversationFinishButton : PingPongDetailIntent

    data object ClickCloseAlert : PingPongDetailIntent

    data object ClickConfirmAlert : PingPongDetailIntent

    data object ClickGoToKakaoTalkButton : PingPongDetailIntent

    data object ClickOtherOpenBottleButton : PingPongDetailIntent

    data class ClickSendLetter(
        val order: Int,
        val text: String
    ) : PingPongDetailIntent

    data class OnFocusedTextField(
        val order: Int,
        val isFocused: Boolean
    ) : PingPongDetailIntent

    data class OnLetterTextChange(
        val order: Int,
        val text: String
    ) : PingPongDetailIntent

    data class ClickLetterCard(val order: Int) : PingPongDetailIntent

    data object ClickPhotoCard : PingPongDetailIntent

    data object ClickLikeSharePhotoButton : PingPongDetailIntent

    data object ClickHateSharePhotoButton : PingPongDetailIntent

    data class ClickShareProfilePhoto(val willShare: Boolean) : PingPongDetailIntent

    data object ClickKakaoShareCard : PingPongDetailIntent

    data object ClickLikeShareKakaoIdButton : PingPongDetailIntent

    data object ClickHateShareKakaoIdButton : PingPongDetailIntent

    data class ClickShareKakaoId(val willMatch: Boolean) : PingPongDetailIntent

    data object RefreshPingPongDetail : PingPongDetailIntent

    data object ChangeRefreshState : PingPongDetailIntent

}