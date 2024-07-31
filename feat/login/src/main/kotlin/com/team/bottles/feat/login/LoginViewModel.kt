package com.team.bottles.feat.login

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.KakaoClinetResult
import com.team.bottles.core.domain.auth.usecase.LoginWithKakaoUseCase
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginSideEffect
import com.team.bottles.feat.login.mvi.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithKakaoUseCase: LoginWithKakaoUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<LoginUiState, LoginSideEffect, LoginIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LoginUiState =
        LoginUiState()

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClickKakaoLoginButton -> kakaoLoin(thirdPartyAccessToken = intent.accessToken)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun kakaoLoin(thirdPartyAccessToken: KakaoClinetResult) {
        launch {
            val isSignUp = loginWithKakaoUseCase(accessToken = thirdPartyAccessToken.accessToken).isSignUp

            if (isSignUp) {
                postSideEffect(LoginSideEffect.NavigateToSandBeach)
            } else {
                postSideEffect(LoginSideEffect.NavigateToOnboarding)
            }
        }
    }

}