package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.PartnerBubble

@Composable
internal fun PartnerReject() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        PartnerBubble(text = "공유를 거절했어요")
        PartnerBubble(text = "상대방이 대화를 중단했어요")
    }
}