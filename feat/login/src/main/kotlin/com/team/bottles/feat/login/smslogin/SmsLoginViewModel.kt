package com.team.bottles.feat.login.smslogin

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginIntent
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginSideEffect
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmsLoginViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SmsLoginUiState, SmsLoginSideEffect, SmsLoginIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SmsLoginUiState =
        SmsLoginUiState

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SmsLoginIntent) {
        when (intent) {
            is SmsLoginIntent.ClickWebLoginButton -> smsLogin(smsLoginWebResult = intent.smsLoginResult)
            is SmsLoginIntent.ClickWebCloseButton -> navigateToLoginEndPoint()
        }
    }

    private fun smsLogin(smsLoginWebResult: SmsLoginWebResult) {
        launch {
            webViewConnectUseCase.setLocalToken(token = Token(
                accessToken = smsLoginWebResult.accessToken,
                refreshToken = smsLoginWebResult.refreshToken))
            if (smsLoginWebResult.hasCompleteUserProfile) { // 프로필 생성을 했다면
                postSideEffect(SmsLoginSideEffect.NavigateToSandBeach)
            } else { // 프로필 생성을 안했으면
                postSideEffect(SmsLoginSideEffect.NavigateToOnboarding)
            }
        }
    }

    private fun navigateToLoginEndPoint() {
        postSideEffect(SmsLoginSideEffect.NavigateToLoginEndPoint)
    }

}
