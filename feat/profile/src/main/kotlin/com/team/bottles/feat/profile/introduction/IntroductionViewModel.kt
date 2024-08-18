package com.team.bottles.feat.profile.introduction

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.profile.usecase.UploadProfileImageUseCase
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent
import com.team.bottles.feat.profile.introduction.mvi.IntroductionSideEffect
import com.team.bottles.feat.profile.introduction.mvi.IntroductionStep
import com.team.bottles.feat.profile.introduction.mvi.IntroductionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val createIntroductionUseCase: CreateIntroductionUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<IntroductionUiState, IntroductionSideEffect, IntroductionIntent>(savedStateHandle) {

    init {
        launch {
            getUserProfileUseCase().profileSelect.run {
                reduce {
                    copy(keyPoints = UserKeyPoint.introduction(
                        keyWords = listOf(job, mbti, region.city, height.toString(), smoking, alcohol),
                        personality = keyword,
                        hobbies = interest.run { etc + sports + entertainment + culture })
                    )
                }
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): IntroductionUiState =
        IntroductionUiState()

    override suspend fun handleIntent(intent: IntroductionIntent) {
        when (intent) {
            is IntroductionIntent.ClickBackButton -> onClickBackButton()
            is IntroductionIntent.ChangeIntroduction -> textChange(text = intent.text)
            is IntroductionIntent.ClickBottomButton -> onClickBottomButton()
            is IntroductionIntent.OnFocusedTextField -> changeTextFieldState()
            is IntroductionIntent.ClickPhoto -> reduce { copy(imageFile = intent.file) }
            is IntroductionIntent.ClickDeleteButton -> reduce { copy(imageFile = null) }
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
            IntroductionStep.INPUT_INTRODUCTION -> createIntroduction()
            IntroductionStep.SELECT_USER_IMAGE -> uploadProfileImage()
        }
    }

    private fun createIntroduction() {
        launch {
            createIntroductionUseCase(
                questionsAndAnswers = listOf(
                    QuestionAndAnswer(
                        question = "",
                        answer = currentState.introduce)
                    )
            )

            reduce { copy(step = IntroductionStep.SELECT_USER_IMAGE) }
        }
    }

    private fun uploadProfileImage() {
        launch {
            if (currentState.imageFile == null) {
                postSideEffect(IntroductionSideEffect.RequireSelectPhoto(toastMessage = "이미지를 선택해주세요."))
            } else {
                currentState.imageFile?.let { imageFile ->
                    uploadProfileImageUseCase(imageFile = imageFile)
                }
                postSideEffect(IntroductionSideEffect.CompleteIntroduction(toastMessage = "자기소개 작성을 완료했어요."))
            }
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