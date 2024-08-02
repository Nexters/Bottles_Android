package com.team.bottles.feat.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.signup.mvi.SignupIntent
import com.team.bottles.feat.signup.mvi.SignupUiState

@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onIntent: (SignupIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BottlesTheme.color.background.primary)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // webview 구현
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupScreenPreview() {
    BottlesTheme {
        SignupScreen(
            uiState = SignupUiState,
            onIntent = { },
        )
    }
}