package com.team.bottles.core.designsystem.components.etc

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesToggleButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    enabled: Boolean = true,
) {
    val backgroundColor = if (checked) {
        BottlesTheme.color.icon.selected
    } else {
        BottlesTheme.color.icon.disabled
    }
    val shape = RoundedCornerShape(100.dp)

    Box(
        modifier = modifier
            .width(44.dp)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .clip(shape = shape)
            .padding(2.dp)
            .noRippleClickable(
                onClick = onCheckedChange,
                enabled = enabled
            ),
        contentAlignment = if (checked) Alignment.TopEnd else Alignment.TopStart
    ) {
        Canvas(
            modifier = Modifier
                .size(size = 22.dp)
        ) {
            drawCircle(color = Color.White)
        }
    }
}

@Preview
@Composable
private fun BottlesToggleButtonPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesToggleButton(
                checked = false,
                onCheckedChange = {}
            )
            BottlesToggleButton(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}