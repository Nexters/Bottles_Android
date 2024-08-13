package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.etc.UserInfo
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.ui.CardProfile
import com.team.bottles.core.ui.CardQuit
import com.team.bottles.core.ui.LetterCard
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.feat.pingpong.mvi.MatchStatus

internal fun LazyListScope.introductionContents(
    onClickConversationFinish: () -> Unit,
    isStoppedPingPong: Boolean,
    matchStatus: MatchStatus,
    deleteAfterDay: Int,
    partnerProfile: UserProfile,
    partnerKeyPoints: List<UserKeyPoint>,
    partnerLetter: String,
) {
    item {
        when (isStoppedPingPong) {
            false -> {
                UserInfo(
                    imageUrl = partnerProfile.imageUrl,
                    userName = partnerProfile.userName,
                    userAge = partnerProfile.age
                )

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

                LetterCard(
                    title = stringResource(
                        id = R.string.user_name_send_letter,
                        formatArgs = arrayOf(partnerProfile.userName)
                    ),
                    content = partnerLetter
                )
            }

            true -> {
                CardQuit(
                    userName = partnerProfile.userName,
                    deleteDay = deleteAfterDay
                )
            }
        }

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.small))

        CardProfile(keyPoints = partnerKeyPoints)

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

        Text(
            modifier = Modifier
                .debounceNoRippleClickable(
                    onClick = onClickConversationFinish,
                    enabled = matchStatus == MatchStatus.IN_CONVERSATION
                ),
            text = stringResource(id = R.string.conversation_finish),
            style = BottlesTheme.typography.subTitle2,
            color = if(matchStatus == MatchStatus.IN_CONVERSATION) BottlesTheme.color.text.enabledSecondary
            else BottlesTheme.color.text.disabledSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroductionContentsPreview() {
    BottlesTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(
                horizontal = BottlesTheme.spacing.medium
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            introductionContents(
                onClickConversationFinish = {},
                isStoppedPingPong = false,
                matchStatus = MatchStatus.MATCH_FAILED,
                deleteAfterDay = 0,
                partnerProfile = UserProfile.sampleUserProfile(),
                partnerKeyPoints = UserKeyPoint.exampleUerKeyPoints(),
                partnerLetter = "편지내용입니다.",
            )
        }
    }
}