package com.team.bottles.core.designsystem.components.textfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesTextFieldErrorCaption(
    text: String
) {
    Text(
        text = text,
        style = BottlesTheme.typography.caption,
        color = BottlesTheme.color.text.errorPrimary
    )
}