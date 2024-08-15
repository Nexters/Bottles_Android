package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesLetterDropDownButton
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.ui.PartnerBubble
import com.team.bottles.core.ui.UserBubble
import com.team.bottles.feat.pingpong.mvi.ShareSelectButtonState

@Composable
internal fun KakaoShareCard(
    onClickKakaoShareCard: () -> Unit,
    onClickLikeShareKakaoId: () -> Unit,
    onClickHateShareKakaoId: () -> Unit,
    onClickShareKakaoId: (Boolean) -> Unit,
    pingPongMatchStatus: PingPongMatchStatus,
    selectState: ShareSelectButtonState,
    isExpanded: Boolean,
    isFirstSelect: Boolean
) {
    BottlesLetterDropDownButton(
        onClickButton = onClickKakaoShareCard,
        text = "최종 선택",
        isExpanded = isExpanded,
        isEnabled = pingPongMatchStatus != PingPongMatchStatus.NONE
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraLarge
            )
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = BottlesTheme.color.border.secondary
            )

            Text(
                text = "나의 카카오톡 아이디를 공유할까요?",
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.secondary
            )

            when (pingPongMatchStatus) {
                PingPongMatchStatus.NONE -> {}
                PingPongMatchStatus.MATCH_SUCCEEDED -> MathSucceeded(isFirstSelect = isFirstSelect)
                PingPongMatchStatus.WAITING_OTHER_ANSWER -> WaitingOtherAnswer()
                PingPongMatchStatus.MATCH_FAILED -> PartnerReject()
                PingPongMatchStatus.REQUIRE_SELECT -> SelectYesOrNo(
                    onClickAgree = onClickLikeShareKakaoId,
                    onClickReject = onClickHateShareKakaoId,
                    onClickComplete = onClickShareKakaoId,
                    selectState = selectState
                )
            }
        }
    }
}

@Composable
private fun WaitingOtherAnswer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        UserBubble(text = "선택을 완료했어요")
        PartnerBubble(text = "상대방의 선택을 기다리고 있어요")
        PartnerBubble(text = "두근두근, 매칭이 이뤄질까요?")
    }
}

@Composable
private fun MathSucceeded(
    isFirstSelect: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        if (isFirstSelect) {
            UserBubble(text = "선택을 완료했어요")
            PartnerBubble(text = "선택을 완료했어요")
        } else {
            PartnerBubble(text = "선택을 완료했어요")
            UserBubble(text = "선택을 완료했어요")
        }
        PartnerBubble(text = "매칭 탭에서 결과를 확인해주세요!")
    }
}

@Preview
@Composable
private fun FinalCardPreview() {
    BottlesTheme {
        KakaoShareCard(
            onClickKakaoShareCard = { /*TODO*/ },
            onClickShareKakaoId = {},
            onClickLikeShareKakaoId = {},
            onClickHateShareKakaoId = {},
            pingPongMatchStatus = PingPongMatchStatus.NONE,
            selectState = ShareSelectButtonState.NONE,
            isExpanded = true,
            isFirstSelect = true
        )
    }
}