package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.CardKakaoId
import com.team.bottles.feat.pingpong.mvi.MatchingResult

internal fun LazyListScope.matchingContents(
    matchingResult: MatchingResult,
    partnerName: String,
    kakaoId: String
) {
    item(key = "Matching Contents") {
        val title = when (matchingResult) {
            MatchingResult.WAITING -> "${partnerName}님의\n결정을 기다리고 있어요"
            MatchingResult.SUCCESS -> "축하해요! 지금부터 찐-하게\n서로를 알아가 보세요"
            MatchingResult.FAIL -> "다른 보틀을\n열어보는 건 어때요?"
        }

        val subTitle = when (matchingResult) {
            MatchingResult.WAITING -> "조금만 더 기다려봐요!"
            MatchingResult.SUCCESS -> "아이디를 복사해 더 깊은 대화를 나눠보세요"
            MatchingResult.FAIL -> "아쉽지만 매칭에 실패했어요"
        }

        MatchingContentsTitle(
            title = title,
            subTitle = subTitle
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

        if (matchingResult == MatchingResult.SUCCESS) {
            CardKakaoId(kakaoId = kakaoId)
        } else {
            CoilImage(
                modifier = Modifier.size(size = 250.dp),
                imageModel = {
                    if (matchingResult == MatchingResult.FAIL) R.drawable.illustration_search_bottle
                    else R.drawable.illustration_phone
                },
                previewPlaceholder = painterResource(id = R.drawable.illustration_search_bottle)
            )
        }
    }
}

@Composable
private fun MatchingContentsTitle(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        Text(
            text = title,
            style = BottlesTheme.typography.title2,
            color = BottlesTheme.color.text.primary
        )
        Text(
            text = subTitle,
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.tertiary
        )
    }
}

/*=========Preview=========*/

@Preview(showBackground = true)
@Composable
private fun MatchingContentsStayPreview() {
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
            matchingContents(
                matchingResult = MatchingResult.WAITING,
                kakaoId = "asdasf123",
                partnerName = "뇽뇽이"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchingContentsSuccessPreview() {
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
            matchingContents(
                matchingResult = MatchingResult.SUCCESS,
                kakaoId = "asdasf123",
                partnerName = "뇽뇽이"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchingContentsFailPreview() {
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
            matchingContents(
                matchingResult = MatchingResult.FAIL,
                kakaoId = "asdasf123",
                partnerName = "뇽뇽이"
            )
        }
    }
}