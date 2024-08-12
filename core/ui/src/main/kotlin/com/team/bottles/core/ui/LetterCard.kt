package com.team.bottles.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun LetterCard(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    Column(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.primary,
                shape = BottlesTheme.shape.extraLarge
            )
            .clip(shape = BottlesTheme.shape.extraLarge)
            .border(
                width = 1.dp,
                shape = BottlesTheme.shape.extraLarge,
                color = BottlesTheme.color.border.primary
            )
            .padding(
                horizontal = BottlesTheme.spacing.medium,
                vertical = BottlesTheme.spacing.extraLarge
            ),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraLarge
        )
    ) {
        Text(
            text = title,
            style = BottlesTheme.typography.subTitle1,
            color = BottlesTheme.color.text.primary
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = BottlesTheme.color.onContainer.primary,
                    shape = BottlesTheme.shape.small
                )
                .clip(shape = BottlesTheme.shape.small)
                .padding(paddingValues = BottlesTheme.padding.medium)
        ) {
            Text(
                text = content,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
        }
    }
}

@Preview
@Composable
private fun LetterCardPreview() {
    BottlesTheme {
        LetterCard(
            title = "내가 쓴 편지",
            content = "편지 내용",
        )
    }
}