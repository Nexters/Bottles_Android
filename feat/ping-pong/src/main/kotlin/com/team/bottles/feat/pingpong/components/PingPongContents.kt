package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesLetterDropDownButton
import com.team.bottles.core.designsystem.components.textfield.BottlesLinesTextFieldWithButton
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.ui.PartnerBubble
import com.team.bottles.core.ui.UserBubble
import com.team.bottles.feat.pingpong.mvi.PingPongCard

internal fun LazyListScope.pingPongContents(
    pingPongCards: List<PingPongCard>,
    onClickLetterCard: (order: Int) -> Unit,
    onValueChange: (order: Int, text: String) -> Unit,
    onFocusedTextField: (order: Int, isFocused: Boolean) -> Unit,
    onClickSendLetter: (order: Int, text: String) -> Unit
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

            is PingPongCard.Photo -> {}
            is PingPongCard.Final -> {}
        }

        Spacer(modifier = Modifier.height(BottlesTheme.spacing.small))
    }
}

@Composable
private fun LetterCard(
    onClickSendLetter: (order: Int, text: String) -> Unit,
    onClickLetterCard: (order: Int) -> Unit,
    onFocusedTextField: (order: Int, isFocused: Boolean) -> Unit,
    inputLetter: String,
    onValueChange: (order: Int, text: String) -> Unit,
    letter: PingPongLetter,
    isEnabled: Boolean,
    isExpanded: Boolean,
    maxLength: Int,
    textFiledState: BottlesTextFieldState,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isTextFieldFocused) {
        onFocusedTextField(letter.order, isTextFieldFocused)
    }

    BottlesLetterDropDownButton(
        onClickButton = { onClickLetterCard(letter.order) },
        text = "${letter.order + 1} 번째 질문",
        isExpanded = isExpanded,
        isEnabled = isEnabled
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
                text = letter.question,
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.secondary
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    space = BottlesTheme.spacing.small
                )
            ) {
                if (letter.isDone) { // 둘다 답변을 한 상태
                    PartnerBubble(text = letter.otherAnswer)
                    UserBubble(text = letter.myAnswer)
                } else {
                    if (!letter.shouldAnswer) { // 내가 답변을 했지만, 상대는 답변을 안한 상태
                        UserBubble(text = letter.myAnswer)
                        PartnerBubble(text = "상대방의 답변을 기다리고 있어요")
                    } else {
                        if (letter.otherAnswer.isEmpty()) { // 내가 답변을 안하고, 상대가 답변을 안한 상태
                            PartnerBubble(text = "상대방의 답변을 기다리고 있어요")
                        } else { // 내가 답변을 안하고, 상대가 답변을 한 상태
                            PartnerBubble(text = "상대방의 답변이 도착했어요")
                            PartnerBubble(text = "답변을 작성하면 확인할 수 있어요!")
                        }
                        BottlesLinesTextFieldWithButton(
                            value = inputLetter,
                            onValueChange = { text -> onValueChange(letter.order, text) },
                            onClickButton = { onClickSendLetter(letter.order, inputLetter) },
                            hint = "솔직하게 작성할수록 서로를 알아가는데 도움이 돼요",
                            maxLength = maxLength,
                            state = textFiledState,
                            buttonText = "작성 완료",
                            interactionSource = interactionSource
                        )
                    }
                }
            }
        }
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
                onClickSendLetter = { _, _ -> }
            )
        }
    }
}