package com.team.bottles.feat.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.login.component.BottomButtons
import com.team.bottles.feat.login.component.Symbol
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginUiState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bg_login),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Symbol()
        BottomButtons(
            onClickKakaoLogin = { onIntent(LoginIntent.ClickKakaoLoginButton) },
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    BottlesTheme {
        LoginScreen(
            uiState = LoginUiState,
            onIntent = { },
        )
    }
}