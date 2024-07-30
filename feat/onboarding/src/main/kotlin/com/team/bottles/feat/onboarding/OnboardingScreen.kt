package com.team.bottles.feat.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.onboarding.mvi.OnboardingIntent
import com.team.bottles.feat.onboarding.mvi.OnboardingUiState

@Composable
internal fun OnboardingScreen(
    uiState: OnboardingUiState,
    onIntent: (OnboardingIntent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BottlesTopBar() },
        bottomBar = {
            Box(
                modifier = Modifier
                    .height(88.dp)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 24.dp
                    )
            ) {
                BottlesSolidButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    buttonType = SolidButtonType.LG,
                    text = "다음",
                    onClick = { onIntent(OnboardingIntent.ClickNextButton) }
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "온보딩 화면 ${uiState.page}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    BottlesTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(),
            onIntent = { }
        )
    }
}