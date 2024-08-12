package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun PingPongContents(
    modifier: Modifier = Modifier,
    onIntent: () -> Unit,
    scrollState: LazyListState,
    content: @Composable LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scrollState,
        contentPadding = PaddingValues(
            horizontal = BottlesTheme.spacing.medium
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

            // TODO 컨텐츠 적기

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

            Text(
                modifier = Modifier
                    .debounceNoRippleClickable(
                        onClick = onIntent
                    ),
                text = stringResource(id = R.string.conversation_finish),
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.enabledSecondary
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraSmall))
        }
    }
}