package com.team.bottles.core.designsystem.components.etc.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun EtcText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.secondary,
                shape = BottlesTheme.shape.extraSmall,
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
    ) {
        Text(
            text = text,
            color = BottlesTheme.color.text.quinary,
            style = BottlesTheme.typography.body,
        )
    }
}

@Composable
fun BottlesEtcText(
    modifier: Modifier = Modifier,
    leftText: String? = null,
    rightText: String
) {
    Row(
        modifier = modifier
            .background(
                color = BottlesTheme.color.onContainer.secondary,
                shape = BottlesTheme.shape.extraSmall
            )
            .height(height = 29.dp)
            .padding(horizontal = BottlesTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraSmall
        ),
    ) {
        if (leftText != null) {
            Text(
                text = leftText,
                style = BottlesTheme.typography.caption,
                color = BottlesTheme.color.text.secondary
            )
            Text(
                text = "|",
                style = BottlesTheme.typography.caption,
                color = BottlesTheme.color.border.primary
            )
        }
        Text(
            text = rightText,
            style = BottlesTheme.typography.caption,
            color = BottlesTheme.color.text.tertiary
        )
    }
}

@Preview
@Composable
fun EtcTextPreview() {
    BottlesTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EtcText(
                text = "text",
            )
            BottlesEtcText(
                leftText = "사진을 공유했어요",
                rightText = "00시간 전"
            )
            BottlesEtcText(
                rightText = "00시간 전"
            )
        }
    }
}