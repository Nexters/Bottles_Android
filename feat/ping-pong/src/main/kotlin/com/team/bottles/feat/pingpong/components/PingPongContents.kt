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
import com.team.bottles.core.domain.bottle.model.PhotoCardStatus
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.feat.pingpong.mvi.PingPongCard

internal fun LazyListScope.pingPongContents(
    pingPongCards: List<PingPongCard>,
    onClickLetterCard: (order: Int) -> Unit,
    onValueChange: (order: Int, text: String) -> Unit,
    onFocusedTextField: (order: Int, isFocused: Boolean) -> Unit,
    onClickSendLetter: (order: Int, text: String) -> Unit,
    onClickPhotoCard: () -> Unit,
    onClickLikeShare: () -> Unit,
    onClickHateShare: () -> Unit,
    onClickShareProfilePhoto: () -> Unit
) {
    items(
        items = pingPongCards,
        key = { card ->
            when (card) {
                is PingPongCard.Letter -> "Letter-${card.letter.order}"
                is PingPongCard.Photo -> "Photo"
                is PingPongCard.Final -> "Final"
            }
        }
    ) { card ->
        when (card) {
            is PingPongCard.Letter -> {
                LetterCard(
                    onClickSendLetter = onClickSendLetter,
                    onClickLetterCard = onClickLetterCard,
                    letter = card.letter,
                    isEnabled = card.isEnabled,
                    isExpanded = card.isExpanded,
                    inputLetter = card.text,
                    onValueChange = onValueChange,
                    maxLength = card.maxLength,
                    onFocusedTextField = onFocusedTextField,
                    textFiledState = card.textFiledState
                )
            }

            is PingPongCard.Photo -> {
                PhotoCard(
                    photo = card.photo,
                    onClickPhotoCard = onClickPhotoCard,
                    onClickLikeShare = onClickLikeShare,
                    onClickHateShare = onClickHateShare,
                    onClickShareProfilePhoto = onClickShareProfilePhoto,
                    isExpanded = card.isExpanded,
                    isEnabled = card.photo.photoCardStatus != PhotoCardStatus.NONE,
                    selectState = card.selectButtonState
                )
            }
            is PingPongCard.Final -> {}
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
                    PingPongCard.Final()
                ),
                onClickLetterCard = {},
                onValueChange = { _, _ -> },
                onFocusedTextField = { _, _ -> },
                onClickSendLetter = { _, _ -> },
                onClickShareProfilePhoto = {},
                onClickHateShare = {},
                onClickLikeShare = {},
                onClickPhotoCard = {}
            )
        }
    }
}