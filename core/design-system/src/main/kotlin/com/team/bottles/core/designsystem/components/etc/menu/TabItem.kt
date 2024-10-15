package com.team.bottles.core.designsystem.components.etc.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

enum class BottlesTabItemState {
    SELECTED,
    ENABLED,
    DISABLED,
    ;
}

@Composable
fun BottlesTabItem(
    modifier: Modifier = Modifier,
    text: String,
    state: BottlesTabItemState,
    onClickTab: () -> Unit
) {
    val textColor = when (state) {
        BottlesTabItemState.DISABLED -> BottlesTheme.color.text.disabledSecondary
        BottlesTabItemState.ENABLED -> BottlesTheme.color.text.enabledSecondary
        BottlesTabItemState.SELECTED -> BottlesTheme.color.text.selectedPrimary
    }
    val textStyle = when (state) {
        BottlesTabItemState.DISABLED,
        BottlesTabItemState.ENABLED -> BottlesTheme.typography.body

        BottlesTabItemState.SELECTED -> BottlesTheme.typography.subTitle2
    }
    val dividerColor = BottlesTheme.color.border.selected

    Box(
        modifier = Modifier
            .height(height = 42.dp)
            .drawBehind {
                if (state == BottlesTabItemState.SELECTED) {
                    drawRoundRect(
                        color = dividerColor,
                        size = Size(
                            width = size.width,
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 0f,
                            y = size.height - 2.dp.toPx()
                        ),
                        cornerRadius = CornerRadius(4.dp.toPx())
                    )
                }
            }
            .noRippleClickable(onClick = onClickTab)
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(horizontal = BottlesTheme.spacing.small),
            text = text,
            style = textStyle,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TabItemPreview() {
    BottlesTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesTabItem(
                text = "sad",
                state = BottlesTabItemState.ENABLED,
                onClickTab = {}
            )

            BottlesTabItem(
                text = "sad",
                state = BottlesTabItemState.SELECTED,
                onClickTab = {}
            )

            BottlesTabItem(
                text = "sad",
                state = BottlesTabItemState.DISABLED,
                onClickTab = {}
            )
        }
    }
}