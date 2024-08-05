package com.team.bottles.core.designsystem.components.etc.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme


@Composable
fun ProgressChip(
    modifier: Modifier = Modifier,
    firstNumber: Int,
    secondNUmber: Int,
    separator: String = "/",
    backgroundColor: Color = BottlesTheme.color.container.secondary,
    firstColor: Color = BottlesTheme.color.text.quinary,
    secondColor: Color = BottlesTheme.color.text.senary,
    separatorColor: Color = BottlesTheme.color.text.senary,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BottlesTheme.shape.radius8,
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = firstNumber.toString(),
            color = firstColor,
            style = BottlesTheme.typography.subTitle2,
        )
        Text(
            text = separator,
            color = separatorColor,
            style = BottlesTheme.typography.subTitle2,
        )
        Text(
            text = secondNUmber.toString(),
            color = secondColor,
            style = BottlesTheme.typography.subTitle2,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressButtonPreview() {
    BottlesTheme {
        ProgressChip(
            firstNumber = 0,
            secondNUmber = 0,
        )
    }
}