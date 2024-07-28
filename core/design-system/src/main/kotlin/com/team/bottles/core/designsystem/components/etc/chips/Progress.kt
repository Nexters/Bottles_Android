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
fun ProgressButton(
    number1: String,
    number2: String,
    separator: String = "/",
    modifier: Modifier = Modifier,
    backgroundColor: Color = BottlesTheme.color.container.secondary,
    number1Color: Color = BottlesTheme.color.text.quinary,
    number2Color: Color = BottlesTheme.color.text.senary,
    separatorColor: Color = BottlesTheme.color.text.senary,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .background(
                    color = backgroundColor,
                    shape = BottlesTheme.shape.radius8,
                )
                .width(48.44.dp)
                .height(32.dp)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = number1,
                color = number1Color,
                style = BottlesTheme.typography.subTitle2,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = " $separator",
                color = separatorColor,
                style = BottlesTheme.typography.subTitle2,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = number2,
                color = number2Color,
                style = BottlesTheme.typography.subTitle2,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressButtonPreview() {
    BottlesTheme {
        ProgressButton(
            number1 = "0",
            number2 = "0",
        )
    }
}