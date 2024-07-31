package com.team.bottles.feat.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginUiState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "로그인 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = uiState.text,
            onClick = { onIntent(LoginIntent.ClickLoginButton) },
            contentHorizontalPadding = 12.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BottlesTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onIntent = { }
        )
    }
}