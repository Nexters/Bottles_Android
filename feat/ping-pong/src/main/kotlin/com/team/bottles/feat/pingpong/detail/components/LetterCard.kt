package com.team.bottles.feat.pingpong.detail.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesLetterDropDownButton
import com.team.bottles.core.designsystem.components.textfield.BottlesLinesTextFieldWithButton
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.ui.PartnerBubble
import com.team.bottles.core.ui.UserBubble

@Composable
internal fun LetterCard(
    onClickSendLetter: (order: Int, text: String) -> Unit,
    onClickLetterCard: (order: Int) -> Unit,
    onFocusedTextField: (order: Int, isFocused: Boolean) -> Unit,
    onValueChange: (order: Int, text: String) -> Unit,
    inputLetter: String,
    letter: PingPongLetter,
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
        text = "${letter.order} 번째 질문",
        isExpanded = isExpanded,
        isEnabled = letter.canShow
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
                text = "Q. ${letter.question}",
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