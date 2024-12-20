package com.team.bottles.core.designsystem.components.bars

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
fun BottlesBottomBar(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = false,
    isDebounce: Boolean = true,
) {
    Box(
        modifier = modifier
            .height(height = 88.dp)
            .background(brush = BottlesTheme.color.background.tertiary)
            .padding(horizontal = BottlesTheme.spacing.medium),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottlesSolidButton(
            modifier = Modifier.fillMaxWidth(),
            buttonType = SolidButtonType.LG,
            text = text,
            enabled = enabled,
            onClick = onClick,
            isDebounce = isDebounce,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottlesBottomBarPreview() {
    BottlesTheme {
        BottlesBottomBar(
            text = "다음",
            onClick = {}
        )
    }
}