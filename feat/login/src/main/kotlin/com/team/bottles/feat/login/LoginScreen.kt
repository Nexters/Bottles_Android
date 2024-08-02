package com.team.bottles.feat.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.modifier.debounceClickable
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.login.component.KakaoLoginButton
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginUiState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BottlesTheme.color.background.primary)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CoilImage(
            modifier = Modifier.size(size = 180.dp),
            imageModel = { R.drawable.sample_image }, // TODO : 추후 Lottie로 변경 할 수 있음
            previewPlaceholder = painterResource(id = R.drawable.sample_image)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.branding_message),
                style = BottlesTheme.typography.branding,
                color = BottlesTheme.color.text.secondary
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KakaoLoginButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonText = stringResource(id = R.string.kakao_login),
                    onClick = { onIntent(LoginIntent.ClickKakaoLoginButton) },
                )
                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.spacing12))
                BottlesSolidButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonType = SolidButtonType.LG,
                    text = stringResource(id = R.string.other_login),
                    onClick = { onIntent(LoginIntent.ClickSmsLoginButton) },
                    isDebounce = true
                )
                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.spacing24))
                Text(
                    modifier = Modifier.debounceNoRippleClickable(
                        onClick = { onIntent(LoginIntent.ClickSignupButton) },
                    ),
                    text = stringResource(id = R.string.signup),
                    color = BottlesTheme.color.text.enabledSecondary,
                    style = BottlesTheme.typography.subTitle2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BottlesTheme {
        LoginScreen(
            uiState = LoginUiState,
            onIntent = { },
        )
    }
}