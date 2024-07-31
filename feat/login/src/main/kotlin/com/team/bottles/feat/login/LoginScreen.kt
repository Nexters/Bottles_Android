package com.team.bottles.feat.login

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.login.di.clientEntryPoint
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginUiState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val kakaoClient = remember { activity.clientEntryPoint.kakaoClient() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "로그인 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "카카오 로그인 버튼",
            onClick = {
                coroutineScope.launch {
                    val token = kakaoClient.login()
                    onIntent(LoginIntent.ClickKakaoLoginButton(accessToken = token))
                }
            },
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