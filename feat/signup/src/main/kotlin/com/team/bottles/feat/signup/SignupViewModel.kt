package com.team.bottles.feat.signup

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.signup.mvi.SignupIntent
import com.team.bottles.feat.signup.mvi.SignupSideEffect
import com.team.bottles.feat.signup.mvi.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SignupUiState, SignupSideEffect, SignupIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignupUiState =
        SignupUiState()

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SignupIntent) {
        when (intent) {
            is SignupIntent.ClickSignupButton -> signup(token = intent.token)
            is SignupIntent.ClickWebCloseButton -> navigateToLoginEndPoint()
            is SignupIntent.LoadWebPage -> reduce { copy(isWebPageCanGoBack = intent.canGoBack) }
        }
    }

    private fun signup(token: Token) {
        launch {
            webViewConnectUseCase.setLocalToken(token = token)
            postSideEffect(SignupSideEffect.NavigateToSandBeach)
        }
    }

    private fun navigateToLoginEndPoint() {
        postSideEffect(SignupSideEffect.NavigateToLoginEndPoint)
    }

}
