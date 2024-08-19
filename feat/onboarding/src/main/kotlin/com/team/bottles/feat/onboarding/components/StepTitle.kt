package com.team.bottles.feat.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.components.etc.chips.BottlesProgressChip
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun StepTitle(
    modifier: Modifier = Modifier,
    currentPage: Int,
    maxPage: Int,
    titleText: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BottlesTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraLarge
        )
    ) {
        BottlesProgressChip(
            currentPage = currentPage,
            maxPage = maxPage
        )
        Text(
            text = titleText,
            style = BottlesTheme.typography.title3,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun StepTitlePreview() {
    BottlesTheme {
        StepTitle(
            currentPage = 1,
            maxPage = 2,
            titleText = "천천히 서로를 알아가는 소개팅\n" + "보틀에 오신 것을 환영해요!"
        )
    }
}