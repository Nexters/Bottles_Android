package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonState
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.ui.BottlesRowTab
import com.team.bottles.feat.pingpong.mvi.PingPongTab

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
        modifier = modifier.fillMaxWidth()
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
            text = partnerName,
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

        BottlesRowTab(
            tabs = PingPongTab.entries,
            stateProvider = { tab ->
                when {
                    tab == PingPongTab.MATCHING &&
                            (pingPongMatchStatus == PingPongMatchStatus.NONE || pingPongMatchStatus == PingPongMatchStatus.REQUIRE_SELECT) -> OutlinedButtonState.DISABLED

                    currentTab == tab -> OutlinedButtonState.SELECTED
                    else -> OutlinedButtonState.ENABLED
                }
            },
            onIntent = onClickTab
        )
    }
}