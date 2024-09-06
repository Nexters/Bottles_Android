package com.team.bottles.feat.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesBottomBar
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.onboarding.components.StepFour
import com.team.bottles.feat.onboarding.components.StepOne
import com.team.bottles.feat.onboarding.components.StepThree
import com.team.bottles.feat.onboarding.components.StepTitle
import com.team.bottles.feat.onboarding.components.StepTwo
import com.team.bottles.feat.onboarding.mvi.OnboardingIntent
import com.team.bottles.feat.onboarding.mvi.OnboardingPage
import com.team.bottles.feat.onboarding.mvi.OnboardingUiState

@Composable
internal fun OnboardingScreen(
    uiState: OnboardingUiState,
    onIntent: (OnboardingIntent) -> Unit
) {
    Scaffold(
        containerColor = BottlesTheme.color.background.primary,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = BottlesTheme.spacing.medium)
                            .noRippleClickable(
                                onClick = { onIntent(OnboardingIntent.ClickBackButton) }
                            ),
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = BottlesTheme.color.icon.primary
                    )
                }
            )
        },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

                StepTitle(
                    currentPage = uiState.currentPage.ordinal + 2,
                    maxPage = uiState.maxPage,
                    titleText = uiState.currentPage.title
                )

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

                when (uiState.currentPage) {
                    OnboardingPage.ONE -> StepOne()
                    OnboardingPage.TWO -> StepTwo()
                    OnboardingPage.THREE -> StepThree()
                    OnboardingPage.FOUR -> StepFour()
                }

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
            }

            BottlesBottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = if (uiState.currentPage.ordinal + 2 != uiState.maxPage) "다음"
                else "확인",
                onClick = { onIntent(OnboardingIntent.ClickNextButton) },
                enabled = true
            )
        }
    }
}

@Preview(heightDp = 800, widthDp = 600)
@Preview(heightDp = 800)
@Composable
private fun OnboardingScreenOnePreview() {
    BottlesTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(currentPage = OnboardingPage.ONE),
            onIntent = { }
        )
    }
}

@Preview(heightDp = 800, widthDp = 600)
@Preview(heightDp = 800)
@Composable
private fun OnboardingScreenTwoPreview() {
    BottlesTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(currentPage = OnboardingPage.TWO),
            onIntent = { }
        )
    }
}

@Preview(heightDp = 800, widthDp = 600)
@Preview(heightDp = 800)
@Composable
private fun OnboardingScreenThreePreview() {
    BottlesTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(currentPage = OnboardingPage.THREE),
            onIntent = { }
        )
    }
}

@Preview(heightDp = 800, widthDp = 600)
@Preview(heightDp = 800)
@Composable
private fun OnboardingScreenFourPreview() {
    BottlesTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(currentPage = OnboardingPage.FOUR),
            onIntent = { }
        )
    }
}