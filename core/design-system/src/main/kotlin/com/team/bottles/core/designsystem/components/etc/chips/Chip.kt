package com.team.bottles.core.designsystem.components.etc.chips

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme


@Composable
fun Number(
    number: Int,
    circleColor: Color = BottlesTheme.color.icon.update,
    textColor: Color = BottlesTheme.color.text.quaternary,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(color = circleColor)
        }
        Text(
            text = number.toString(),
            color = textColor,
            style = BottlesTheme.typography.body
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberedCirclePreview() {
    BottlesTheme {
        Number(number = 5)
    }
}