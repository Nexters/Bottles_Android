package com.team.bottles.feat.login.smslogin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginIntent
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginUiState

@Composable
internal fun SmsLoginScreen(
    uiState: SmsLoginUiState,
    onIntent: (SmsLoginIntent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // webview 구현
    }
}

@Preview(showBackground = true)
@Composable
private fun SmsLoginScreenPreview() {
    BottlesTheme {
        SmsLoginScreen(
            uiState = SmsLoginUiState,
            onIntent = { },
        )
    }
}