package com.team.bottles.feat.profile.introduction

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.designsystem.components.textfield.BottlesTextFieldState
import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.usecase.CreateIntroductionUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.profile.usecase.UploadProfileImageUseCase
import com.team.bottles.core.ui.model.UserKeyPoint
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
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
        initIntroduction()
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
            is IntroductionIntent.ClickRetryButton -> retry()
            is IntroductionIntent.ClickErrorScreenBackButton -> closeErrorScreen()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        when (throwable) {
            is BottlesException -> showErrorMessage(throwable.message?: "")
            is BottlesNetworkException -> {
                showErrorMessage(throwable.message?: "")
                showErrorScreen()
            }
            else -> showErrorScreen()
        }
    }

    private fun retry() {
        closeErrorScreen()

        when(currentState.state) {
            IntroductionUiState.IntroductionState.INIT -> initIntroduction()
            IntroductionUiState.IntroductionState.INPUT_INTRODUCTION -> createIntroduction()
            IntroductionUiState.IntroductionState.UPLOAD_IMAGE -> uploadProfileImage()
        }
    }

    private fun showErrorMessage(message: String) {
        postSideEffect(IntroductionSideEffect.ShowErrorMessage(message = message))
    }

    private fun showErrorScreen() {
        reduce { copy(isError = true) }
    }

    private fun closeErrorScreen() {
        if (currentState.isLoading) {
            reduce { copy(isLoading = false, isError = false) }
        } else {
            reduce { copy(isError = false) }
        }
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

    private fun initIntroduction() {
        launch {
            reduce { copy(isLoading = true, state = IntroductionUiState.IntroductionState.INIT) }
            getUserProfileUseCase().profileSelect.run {
                reduce {
                    copy(keyPoints = UserKeyPoint.introduction(
                        keyWords = listOf(job, mbti, region.city, height.toString(), smoking, alcohol),
                        personality = keyword,
                        hobbies = interest.run { etc + sports + entertainment + culture }),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun createIntroduction() {
        launch {
            reduce { copy(state = IntroductionUiState.IntroductionState.INPUT_INTRODUCTION) }
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
            reduce { copy(isLoading = true, state = IntroductionUiState.IntroductionState.UPLOAD_IMAGE) }
            currentState.imageFile?.let { imageFile ->
                uploadProfileImageUseCase(imageFile = imageFile)
            }
            reduce { copy(isLoading = false) }
            postSideEffect(IntroductionSideEffect.CompleteIntroduction(toastMessage = "자기소개 작성을 완료했어요."))
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