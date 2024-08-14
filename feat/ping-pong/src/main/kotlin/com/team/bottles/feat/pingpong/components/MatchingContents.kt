package com.team.bottles.feat.pingpong.components

import androidx.annotation.DrawableRes
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
import com.team.bottles.core.domain.bottle.model.MatchStatus
import com.team.bottles.core.ui.CardKakaoId

internal fun LazyListScope.matchingContents(
    matchStatus: MatchStatus,
    title: String,
    subTitle: String,
    @DrawableRes illustration: Int?,
    kakaoId: String
) {
    item {
        MatchingContentsTitle(
            title = title,
            subTitle = subTitle
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

        when (matchStatus) {
            MatchStatus.IN_CONVERSATION,
            MatchStatus.MATCH_FAILED -> {
                CoilImage(
                    modifier = Modifier.size(size = 250.dp),
                    imageModel = { illustration },
                    previewPlaceholder = painterResource(id = R.drawable.illustration_search_bottle)
                )
            }

            MatchStatus.MATCH_SUCCEEDED -> {
                CardKakaoId(kakaoId = kakaoId)
            }
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
                matchStatus = MatchStatus.IN_CONVERSATION,
                title = "핑퐁 진행중",
                subTitle = "진행중입니다",
                illustration = R.drawable.illustration_phone,
                kakaoId = ""
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
                matchStatus = MatchStatus.MATCH_SUCCEEDED,
                title = "매칭됨",
                subTitle = "매칭되었어요",
                illustration = R.drawable.illustration_phone,
                kakaoId = "카톡아이디"
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
                matchStatus = MatchStatus.MATCH_FAILED,
                title = "매칭 실패",
                subTitle = "매칭에 실패",
                illustration = R.drawable.illustration_search_bottle,
                kakaoId = ""
            )
        }
    }
}