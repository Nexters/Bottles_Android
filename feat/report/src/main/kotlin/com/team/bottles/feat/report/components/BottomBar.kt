package com.team.bottles.feat.report.components

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
internal fun ReportBottomBar(
    modifier: Modifier = Modifier,
    onClickComplete: () -> Unit
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
            text = "완료",
            onClick = onClickComplete
        )
    }
}

@Preview
@Composable
private fun ReportBottomBarPreview() {
    BottlesTheme {
        ReportBottomBar(
            onClickComplete = {}
        )
    }
}