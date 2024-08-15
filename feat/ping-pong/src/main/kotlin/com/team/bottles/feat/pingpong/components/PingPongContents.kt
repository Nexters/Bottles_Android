package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.feat.pingpong.mvi.PingPongCard

internal fun LazyListScope.pingPongContents(
    pingPongCards: List<PingPongCard>,
    pingPongMatchStatus: PingPongMatchStatus,
    onClickLetterCard: (order: Int) -> Unit,
    onValueChange: (order: Int, text: String) -> Unit,
    onFocusedTextField: (order: Int, isFocused: Boolean) -> Unit,
    onClickSendLetter: (order: Int, text: String) -> Unit,
    onClickPhotoCard: () -> Unit,
    onClickLikeSharePhoto: () -> Unit,
    onClickHateSharePhoto: () -> Unit,
    onClickShareProfilePhoto: (Boolean) -> Unit,
    onClickLikeShareKakaoId: () -> Unit,
    onClickHateShareKakaoId: () -> Unit,
    onClickKakaoShareCard: () -> Unit,
    onClickShareKakaoId: (Boolean) -> Unit,
) {
    items(
        items = pingPongCards,
        key = { card ->
            when (card) {
                is PingPongCard.Letter -> "Letter-${card.letter.order}"
                is PingPongCard.Photo -> "Photo"
                is PingPongCard.KakaoShare -> "Kakao Share"
            }
        }
    ) { card ->
        when (card) {
            is PingPongCard.Letter -> {
                LetterCard(
                    onClickSendLetter = onClickSendLetter,
                    onClickLetterCard = onClickLetterCard,
                    onValueChange = onValueChange,
                    onFocusedTextField = onFocusedTextField,
                    letter = card.letter,
                    isExpanded = card.isExpanded,
                    inputLetter = card.text,
                    maxLength = card.maxLength,
                    textFiledState = card.textFiledState
                )
            }

            is PingPongCard.Photo -> {
                PhotoCard(
                    onClickPhotoCard = onClickPhotoCard,
                    onClickLikeSharePhoto = onClickLikeSharePhoto,
                    onClickHateSharePhoto = onClickHateSharePhoto,
                    onClickShareProfilePhoto = onClickShareProfilePhoto,
                    pingPongPhotos = card.pingPongPhotos,
                    pingPongPhotoStatus = card.pingPongPhotoStatus,
                    selectState = card.shareSelectButtonState,
                    isExpanded = card.isExpanded,
                )
            }
            is PingPongCard.KakaoShare -> {
                KakaoShareCard(
                    onClickLikeShareKakaoId = onClickLikeShareKakaoId,
                    onClickHateShareKakaoId = onClickHateShareKakaoId,
                    onClickKakaoShareCard = onClickKakaoShareCard,
                    onClickShareKakaoId = onClickShareKakaoId,
                    pingPongMatchStatus = pingPongMatchStatus,
                    selectState = card.shareSelectButtonState,
                    isExpanded = card.isExpanded,
                    isFirstSelect = card.isFirstSelect
                )
            }
        }

        Spacer(modifier = Modifier.height(BottlesTheme.spacing.small))
    }
}

@Preview(showBackground = true)
@Composable
private fun PingPongContentsPreview() {
    BottlesTheme {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(
                start = BottlesTheme.spacing.medium,
                end = BottlesTheme.spacing.medium,
                top = BottlesTheme.spacing.doubleExtraLarge,
                bottom = BottlesTheme.spacing.extraLarge
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pingPongContents(
                pingPongCards = listOf(
                    PingPongCard.Letter(
                        isExpanded = true,
                        letter = PingPongLetter(
                            question = "Q. 스트레스를 푸는 나만의 방식이 있나요?",
                            canShow = true,
                            order = 0
                        )
                    ),
                    PingPongCard.Letter(
                        letter = PingPongLetter(order = 1)
                    ),
                    PingPongCard.Letter(
                        letter = PingPongLetter(order = 2)
                    ),
                    PingPongCard.Photo(),
                    PingPongCard.KakaoShare()
                ),
                onClickLetterCard = {},
                onValueChange = { _, _ -> },
                onFocusedTextField = { _, _ -> },
                onClickSendLetter = { _, _ -> },
                onClickShareProfilePhoto = {},
                onClickPhotoCard = {},
                onClickHateSharePhoto = {},
                onClickLikeSharePhoto = {},
                onClickHateShareKakaoId = {},
                onClickLikeShareKakaoId = {},
                onClickKakaoShareCard = {},
                onClickShareKakaoId = {},
                pingPongMatchStatus = PingPongMatchStatus.NONE,
            )
        }
    }
}