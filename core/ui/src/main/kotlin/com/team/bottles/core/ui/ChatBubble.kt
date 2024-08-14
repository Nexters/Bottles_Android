package com.team.bottles.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun PartnerBubble(
    modifier: Modifier = Modifier,
    text: String,
    fill: Boolean = false
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(4f, fill)
                .background(
                    color = BottlesTheme.color.onContainer.secondary,
                    shape = RoundedCornerShape(
                        topStart = BottlesTheme.spacing.medium,
                        topEnd = BottlesTheme.spacing.medium,
                        bottomEnd = BottlesTheme.spacing.medium,
                    )
                )
                .padding(
                    vertical = BottlesTheme.spacing.small,
                    horizontal = BottlesTheme.spacing.medium
                )
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun UserBubble(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .weight(4f, false)
                .background(
                    color = BottlesTheme.color.onContainer.primary,
                    shape = RoundedCornerShape(
                        topStart = BottlesTheme.spacing.medium,
                        topEnd = BottlesTheme.spacing.medium,
                        bottomStart = BottlesTheme.spacing.medium,
                    )
                )
                .padding(
                    vertical = BottlesTheme.spacing.small,
                    horizontal = BottlesTheme.spacing.medium
                )
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
        }
    }
}

@Preview
@Composable
private fun PartnerBubblePreview() {
    BottlesTheme {
        Column {
            PartnerBubble(
                text = "상대의 답변을 기다리는 중",
            )
            Spacer(modifier = Modifier.height(5.dp))
            UserBubble(
                text = "상대의 답변을 기다리는 중상대의 답변을 기다리는 중상대의 답변을 기다리는 중상대의 답변을 기다리는 중",
            )
        }
    }
}