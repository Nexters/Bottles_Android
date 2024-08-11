package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
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
import com.team.bottles.feat.pingpong.mvi.IntroductionTabState
import com.team.bottles.feat.pingpong.mvi.PingPongRelationShip

@Composable
internal fun LazyItemScope.IntroductionContents(
    closedDay: Int,
    currentRelationShip: PingPongRelationShip,
    partnerProfile: UserProfile,
    introductionTabState: IntroductionTabState,
    onClickConversationFinish: () -> Unit
) {
    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

    when (currentRelationShip) {
        PingPongRelationShip.ING,
        PingPongRelationShip.SUCCESS -> {
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
                content = introductionTabState.partnerLetter
            )
        }

        PingPongRelationShip.FAIL -> {
            CardQuit(
                userName = partnerProfile.userName,
                deleteDay = closedDay
            )
        }
    }

    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.small))

    CardProfile(keyPoints = introductionTabState.userKeyPoints)

    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

    Text(
        modifier = Modifier
            .debounceNoRippleClickable(
                onClick = onClickConversationFinish
            ),
        text = stringResource(id = R.string.conversation_finish),
        style = BottlesTheme.typography.subTitle2,
        color = BottlesTheme.color.text.enabledSecondary
    )

    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraSmall))
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
            item {
                IntroductionContents(
                    closedDay = 3,
                    currentRelationShip = PingPongRelationShip.FAIL,
                    partnerProfile = UserProfile.sampleUserProfile(),
                    introductionTabState = IntroductionTabState.sample(),
                    onClickConversationFinish = {}
                )
            }
        }
    }
}