package com.team.bottles.feat.pingpong.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.etc.menu.BottlesTabItemState
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.ui.BottlesRowTab
import com.team.bottles.feat.pingpong.detail.mvi.PingPongTab

@Composable
internal fun PingPongTopBar(
    modifier: Modifier = Modifier,
    onClickLeadingIcon: () -> Unit,
    onClickTrailingIcon: () -> Unit,
    onClickTab: (PingPongTab) -> Unit,
    partnerName: String,
    pingPongMatchStatus: PingPongMatchStatus,
    currentTab: PingPongTab,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = BottlesTheme.color.background.primary)
    ) {
        BottlesTopBar(
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .noRippleClickable(
                            onClick = onClickLeadingIcon
                        ),
                    painter = painterResource(id = R.drawable.ic_arrow_left_24),
                    contentDescription = null,
                    tint = BottlesTheme.color.icon.primary
                )
            },
            centerContents = {
                Text(
                    text = partnerName,
                    style = BottlesTheme.typography.body,
                    color = BottlesTheme.color.text.secondary,
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .noRippleClickable(
                            onClick = onClickTrailingIcon
                        ),
                    painter = painterResource(id = R.drawable.ic_siren_24),
                    contentDescription = null,
                    tint = BottlesTheme.color.icon.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.medium))

        BottlesRowTab(
            tabs = PingPongTab.entries,
            stateProvider = { tab ->
                when {
                    tab == PingPongTab.MATCHING &&
                            (pingPongMatchStatus == PingPongMatchStatus.NONE || pingPongMatchStatus == PingPongMatchStatus.REQUIRE_SELECT) -> BottlesTabItemState.DISABLED

                    currentTab == tab -> BottlesTabItemState.SELECTED
                    else -> BottlesTabItemState.ENABLED
                }
            },
            onIntent = onClickTab
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = BottlesTheme.color.border.secondary
        )
    }
}