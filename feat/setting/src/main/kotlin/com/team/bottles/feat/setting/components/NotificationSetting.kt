package com.team.bottles.feat.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.cards.BottlesCard
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithToggleButton
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun NotificationSetting(
    modifier: Modifier = Modifier,
    isFloatingBottle: Boolean,
    isGoodFeelingArrived: Boolean,
    isConversation: Boolean,
    isMarketingResponse: Boolean,
    onChangeFloatingBottle: () -> Unit,
    onChangeGoodFeelingArrived: () -> Unit,
    onChangeConversation: () -> Unit,
    onChangeMarketingResponse: () -> Unit,
) {
    BottlesCard(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.large
        )
    ) {
        BottlesSettingItemWithToggleButton(
            title = "떠다니는 보틀 알림",
            subTitle = "매일 랜덤으로 추천되는 보틀 안내",
            checked = isFloatingBottle,
            onCheckedChange = onChangeFloatingBottle
        )

        BottlesSettingItemWithToggleButton(
            title = "호감 도착 알림",
            subTitle = "내가 받은 호감 안내",
            checked = isGoodFeelingArrived,
            onCheckedChange = onChangeGoodFeelingArrived
        )

        BottlesSettingItemWithToggleButton(
            title = "대화 알림",
            subTitle = "가치관 문답 시작 · 진행 · 중단 , 매칭 안내",
            checked = isConversation,
            onCheckedChange = onChangeConversation
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = BottlesTheme.color.border.secondary,
            thickness = 1.dp
        )

        BottlesSettingItemWithToggleButton(
            title = "마케팅 수신 동의",
            checked = isMarketingResponse,
            onCheckedChange = onChangeMarketingResponse
        )
    }
}

@Preview
@Composable
private fun AccountSettingPreview() {
    BottlesTheme {
         NotificationSetting(
             isFloatingBottle = true,
             isGoodFeelingArrived = true,
             isConversation = true,
             isMarketingResponse = true,
             onChangeFloatingBottle = {},
             onChangeGoodFeelingArrived = {},
             onChangeConversation = {},
             onChangeMarketingResponse = {}
         )
    }
}