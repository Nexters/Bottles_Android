package com.team.bottles.feat.setting.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.setting.components.AccountSetting
import com.team.bottles.feat.setting.components.NotificationSetting
import com.team.bottles.feat.setting.mvi.SettingIntent
import com.team.bottles.feat.setting.mvi.SettingUiState

@Composable
internal fun NotificationSettingScreen(
    uiState: SettingUiState,
    onIntent: (SettingIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BottlesTopBar(
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .noRippleClickable(onClick = { onIntent(SettingIntent.ClickBackButton) }),
                    painter = painterResource(id = R.drawable.ic_arrow_left_24),
                    contentDescription = null,
                    tint = BottlesTheme.color.icon.primary
                )
            },
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        NotificationSetting(
            modifier = Modifier.padding(horizontal = 16.dp),
            isFloatingBottle = uiState.isFloatingBottle,
            isGoodFeelingArrived = uiState.isGoodFeelingArrived,
            isConversation = uiState.isConversation,
            isMarketingResponse = uiState.isMarketingResponse,
            onChangeFloatingBottle = { onIntent(SettingIntent.ClickFloatingBottleToggleButton) },
            onChangeGoodFeelingArrived = { onIntent(SettingIntent.ClickGoodFeelingArrivedToggleButton) },
            onChangeConversation = { onIntent(SettingIntent.ClickConversationToggleButton) },
            onChangeMarketingResponse = { onIntent(SettingIntent.ClickMarketingResponseToggleButton) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationSettingScreenPreview() {
    BottlesTheme {
        NotificationSettingScreen(
            uiState = SettingUiState(),
            onIntent = {},
        )
    }
}