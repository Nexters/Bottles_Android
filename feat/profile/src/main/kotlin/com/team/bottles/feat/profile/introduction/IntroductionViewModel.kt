package com.team.bottles.feat.profile.introduction

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent
import com.team.bottles.feat.profile.introduction.mvi.IntroductionSideEffect
import com.team.bottles.feat.profile.introduction.mvi.IntroductionStep
import com.team.bottles.feat.profile.introduction.mvi.IntroductionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<IntroductionUiState, IntroductionSideEffect, IntroductionIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): IntroductionUiState =
        IntroductionUiState()

    override suspend fun handleIntent(intent: IntroductionIntent) {
        when (intent) {
            is IntroductionIntent.ClickBackButton -> onClickBackButton()
            is IntroductionIntent.ChangeIntroduction -> textChange(text = intent.text)
            is IntroductionIntent.ClickBottomButton -> onClickBottomButton()
            is IntroductionIntent.OnFocusedTextField -> changeTextFieldState()
            is IntroductionIntent.ClickPhoto -> reduce { copy(imageUri = intent.uri) }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun onClickBackButton() {
        when (currentState.step) {
            IntroductionStep.INPUT_INTRODUCTION -> postSideEffect(IntroductionSideEffect.NavigateToSandBeach)
            IntroductionStep.SELECT_USER_IMAGE -> reduce { copy(step = IntroductionStep.INPUT_INTRODUCTION) }
        }
    }

    private fun onClickBottomButton() {
        when (currentState.step) {
            IntroductionStep.INPUT_INTRODUCTION -> reduce { copy(step = IntroductionStep.SELECT_USER_IMAGE) }
            IntroductionStep.SELECT_USER_IMAGE -> postSideEffect(IntroductionSideEffect.NavigateToSandBeach)
        }
    }

    private fun textChange(text: String) {
        reduce { copy(introduce = text) }

        reduce {
            copy(
                introductionTextFiledState =
                if (currentState.introduce.isEmpty()) {
                    BottlesTextFieldState.Enabled
                } else {
                    if (currentState.introduce.length < currentState.minLength ||
                        currentState.introduce.length > currentState.maxLength
                    ) {
                        BottlesTextFieldState.Error
                    } else {
                        BottlesTextFieldState.Focused
                    }
                },
                isEnabledWithBottomButton = currentState.introduce.length >= currentState.minLength
            )
        }
    }

    private fun changeTextFieldState() {
        reduce {
            copy(
                introductionTextFiledState =
                if (currentState.introductionTextFiledState is BottlesTextFieldState.Enabled) {
                    BottlesTextFieldState.Enabled
                } else {
                    if (currentState.introductionTextFiledState != BottlesTextFieldState.Focused &&
                        currentState.introductionTextFiledState != BottlesTextFieldState.Error
                    ) {
                        BottlesTextFieldState.Focused
                    } else {
                        if (currentState.introduce.isEmpty()) {
                            BottlesTextFieldState.Enabled
                        } else {
                            if (currentState.introductionTextFiledState is BottlesTextFieldState.Error) {
                                BottlesTextFieldState.Error
                            } else {
                                BottlesTextFieldState.Active
                            }
                        }
                    }
                }
            )
        }
    }

}