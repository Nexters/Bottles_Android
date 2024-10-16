package com.team.bottles.feat.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertConfirmDialog
import com.team.bottles.core.ui.BottlesErrorScreen
import com.team.bottles.feat.splash.mvi.SplashIntent
import com.team.bottles.feat.splash.mvi.SplashUiState

@Composable
internal fun SplashScreen(
    uiState: SplashUiState,
    onIntent: (SplashIntent) -> Unit
) {
    if (uiState.showDialog) {
        BottlesAlertConfirmDialog(
            onConfirm = { onIntent(SplashIntent.ClickConfirmButton) },
            confirmButtonText = "업데이트 하기",
            title = "업데이트 안내",
            content = "최적의 사용 환경을 위해\n최신 버전의 앱으로 업데이트 해주세요",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF615EFA)),
        verticalArrangement = Arrangement.spacedBy(
            space = (-12).dp, Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(size = 120.dp),
            painter = painterResource(id = R.drawable.bottle_symbol),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(id = R.drawable.bottle_logo_white),
            contentDescription = null,
            tint = Color.White
        )
    }

    if (uiState.isError) {
        BottlesErrorScreen(
            onClickBackButton = { },
            onClickRetryButton = { onIntent(SplashIntent.ClickRetryButton) }
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    BottlesTheme {
        SplashScreen(
            uiState = SplashUiState(
                //showDialog = true,
                isError = true
            ),
            onIntent = {}
        )
    }
}