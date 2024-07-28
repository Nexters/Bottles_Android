package com.team.bottles.core.designsystem.components.etc.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun EtcText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.secondary,
                shape = BottlesTheme.shape.radius8,
            )
            .padding(8.dp),
    ) {
        Text(
            text = text,
            color = BottlesTheme.color.text.quinary,
            style = BottlesTheme.typography.body,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EtcTextPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EtcText(
                text = "text",
            )
        }
    }
}