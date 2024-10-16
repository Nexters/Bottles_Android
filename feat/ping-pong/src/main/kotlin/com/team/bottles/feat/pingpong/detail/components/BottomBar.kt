package com.team.bottles.feat.pingpong.detail.components

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

@Composable
internal fun PingPongBottomBar(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    isMatched: Boolean,
) {
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
            text = if (isMatched) "카카오톡 바로가기" else "다른 보틀 알아보기",
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
            isMatched = true
        )
    }
}