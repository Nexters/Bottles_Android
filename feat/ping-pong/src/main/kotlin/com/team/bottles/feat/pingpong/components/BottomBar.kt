package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.pingpong.mvi.PingPongRelationShip

@Composable
internal fun PingPongBottomBar(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    currentRelationShip: PingPongRelationShip,
) {
    val buttonText = if (currentRelationShip == PingPongRelationShip.SUCCESS) {
        "카카오톡 바로가기"
    } else {
        "다른 보틀 열어보기"
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .background(brush = BottlesTheme.color.background.tertiary)
            .padding(
                horizontal = BottlesTheme.spacing.medium
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottlesSolidButton(
            modifier = Modifier.fillMaxWidth(),
            buttonType = SolidButtonType.LG,
            text = buttonText,
            onClick = onClickButton
        )
    }
}

@Preview
@Composable
private fun PingPongBottomBarPreview() {
    BottlesTheme {
        PingPongBottomBar(
            onClickButton = { /*TODO*/ },
            currentRelationShip = PingPongRelationShip.FAIL
        )
    }
}