package com.team.bottles.feat.profile.introduction

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent
import com.team.bottles.feat.profile.introduction.mvi.IntroductionSideEffect
import com.team.bottles.feat.profile.introduction.mvi.IntroductionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<IntroductionUiState, IntroductionSideEffect, IntroductionIntent>(savedStateHandle) {

    init {
        launch {
            webViewConnectUseCase.getLocalToken().run {
                reduce {
                    copy(
                        token = Token(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    )
                }
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): IntroductionUiState =
        IntroductionUiState()

    override suspend fun handleIntent(intent: IntroductionIntent) {
        when (intent) {
            is IntroductionIntent.ClickWebCompleteButton -> navigateToSandBeach()
            is IntroductionIntent.ClickWebCloseButton -> navigateToSandBeach()
            is IntroductionIntent.ShowToastMessage -> showToastMessage(message = intent.message)
        }
    }

    override fun handleClientException(throwable: Throwable) { }

    private fun navigateToSandBeach() {
        postSideEffect(IntroductionSideEffect.NavigateToSandBeach)
    }

    private fun showToastMessage(message: String) {
        postSideEffect(IntroductionSideEffect.ShowToastMessage(message = message))
    }

}