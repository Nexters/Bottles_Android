package com.team.bottles.feat.login

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginSideEffect
import com.team.bottles.feat.login.mvi.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

): BaseViewModel<LoginUiState, LoginSideEffect, LoginIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LoginUiState =
        LoginUiState()

    override suspend fun handleIntent(intent: LoginIntent) {
        when(intent) {
            is LoginIntent.ClickLoginButton -> navigateToOnboarding()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToOnboarding() {
        postSideEffect(LoginSideEffect.NavigateToOnboarding)
    }

}