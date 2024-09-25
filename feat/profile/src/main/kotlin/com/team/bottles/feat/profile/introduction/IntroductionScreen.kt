package com.team.bottles.feat.profile.introduction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesBottomBar
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.textfield.BottlesLinesMaxLengthTextField
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesErrorScreen
import com.team.bottles.core.ui.BottlesLoadingScreen
import com.team.bottles.core.ui.CardProfile
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.feat.profile.introduction.component.SelectImageCard
import com.team.bottles.feat.profile.introduction.component.Title
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent
import com.team.bottles.feat.profile.introduction.mvi.IntroductionStep
import com.team.bottles.feat.profile.introduction.mvi.IntroductionUiState

@Composable
internal fun IntroductionScreen(
    uiState: IntroductionUiState,
    onIntent: (IntroductionIntent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val scrollState = rememberScrollState()

    BackHandler(enabled = !uiState.isLoading) {
        onIntent(IntroductionIntent.ClickBackButton)
    }

    LaunchedEffect(isFocused) {
        onIntent(IntroductionIntent.OnFocusedTextField)
    }

    Box {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            topBar = {
                BottlesTopBar(
                    modifier = Modifier.background(color = BottlesTheme.color.background.primary),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier
                                .noRippleClickable(
                                    onClick = { onIntent(IntroductionIntent.ClickBackButton) }
                                ),
                            painter = painterResource(id = R.drawable.ic_arrow_left_24),
                            contentDescription = null,
                            tint = BottlesTheme.color.icon.primary
                        )
                    }
                )
            },
            bottomBar = {
                BottlesBottomBar(
                    text = uiState.step.buttonText,
                    onClick = { onIntent(IntroductionIntent.ClickBottomButton) },
                    enabled = uiState.isEnabledWithBottomButton
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BottlesTheme.color.background.primary)
                        .padding(horizontal = BottlesTheme.spacing.medium)
                        .verticalScroll(state = scrollState),
                ) {
                    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

                    Title(
                        currentPage = uiState.step.page,
                        maxPage = IntroductionStep.entries.size,
                        title = uiState.step.title,
                        subTitle = uiState.step.subTitle
                    )

                    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

                    when (uiState.step) {
                        IntroductionStep.INPUT_INTRODUCTION -> {
                            BottlesLinesMaxLengthTextField(
                                value = uiState.introduce,
                                onValueChange = { text ->
                                    onIntent(IntroductionIntent.ChangeIntroduction(text = text))
                                },
                                hint = stringResource(id = R.string.introduction_hint),
                                maxLength = uiState.maxLength,
                                state = uiState.introductionTextFiledState,
                                interactionSource = interactionSource
                            )

                            if (uiState.introductionTextFiledState is BottlesTextFieldState.Error) {
                                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraSmall))

                                Text(
                                    text = "최소 ${uiState.minLength}글자 이상 작성해주세요",
                                    style = BottlesTheme.typography.caption,
                                    color = BottlesTheme.color.text.errorPrimary
                                )
                            }

                            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.small))

                            CardProfile(keyPoints = uiState.keyPoints)
                        }

                        IntroductionStep.SELECT_USER_IMAGE -> {
                            SelectImageCard(
                                imageFile = uiState.imageFile,
                                onIntent = onIntent
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
                }
            }
        }

        if (uiState.isLoading) {
            BottlesLoadingScreen()
        }
    }

    if (uiState.isError) {
        BottlesErrorScreen(
            onClickBackButton = { onIntent(IntroductionIntent.ClickBackButton) },
            onClickRetryButton = { onIntent(IntroductionIntent.ClickRetryButton) },
            isVisibleLeadingIcon = true
        )
    }
}

@Preview(heightDp = 1100)
@Composable
private fun IntroductionScreenStep1Preview() {
    BottlesTheme {
        IntroductionScreen(
            uiState = IntroductionUiState(
                isLoading = true,
                //isError = true,
                step = IntroductionStep.INPUT_INTRODUCTION,
                keyPoints = UserKeyPoint.exampleUerKeyPoints(),
                introduce = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",
                introductionTextFiledState = BottlesTextFieldState.Active
            ),
            onIntent = {}
        )
    }
}

@Preview
@Composable
private fun IntroductionScreenStep2Preview() {
    BottlesTheme {
        IntroductionScreen(
            uiState = IntroductionUiState(
                step = IntroductionStep.SELECT_USER_IMAGE,
                keyPoints = UserKeyPoint.exampleUerKeyPoints(),
                //imageFile = File.createTempFile("asd","xcf"),
            ),
            onIntent = {}
        )
    }
}