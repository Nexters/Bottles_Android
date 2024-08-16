package com.team.bottles.feat.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_login),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Symbol()
            BottomButtons(
                onClickKakaoLogin = { onIntent(LoginIntent.ClickKakaoLoginButton) },
                onClickSignup = { onIntent(LoginIntent.ClickSignupButton) },
                onClickNormalLogin = { onIntent(LoginIntent.ClickSmsLoginButton) }
            )
        }
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