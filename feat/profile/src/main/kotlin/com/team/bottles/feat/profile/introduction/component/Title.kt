package com.team.bottles.feat.profile.introduction.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.components.etc.chips.ProgressChip
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun Title(
    currentPage: Int,
    maxPage: Int,
    title: String,
    subTitle: String
) {
    Column {
        ProgressChip(
            firstNumber = currentPage,
            secondNUmber = maxPage
        )
        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
        Text(
            text = title,
            style = BottlesTheme.typography.title1,
            color = BottlesTheme.color.text.primary
        )
        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.small))
        Text(
            text = subTitle,
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.tertiary
        )
    }
}

@Preview
@Composable
private fun TitlePreview() {
    BottlesTheme {
        Column {
            Title(
                currentPage = 1,
                maxPage = 2,
                title = "보틀에 담을\n소개를 작성해 주세요",
                subTitle = "수정이 어려우니 신중하게 작성해 주세요"
            )
        }
    }
}
