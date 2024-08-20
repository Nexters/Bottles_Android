package com.team.bottles.feat.login

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.KakaoClientResult
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
        LoginUiState

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClickKakaoLoginButton -> startKakaoClient()
            is LoginIntent.KakaoLogin -> kakaoLoin(kakaoClientResult = intent.kakaoClientResult)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun kakaoLoin(kakaoClientResult: KakaoClientResult) {
        launch {
            loginWithKakaoUseCase(accessToken = kakaoClientResult.accessToken).run {
                if (isSignUp) { // 회원가입일 경우
                    postSideEffect(LoginSideEffect.NavigateToOnboarding)
                } else { // 로그인 일 경우
                    if (hasCompleteUserProfile) { // 프로필 생성을 했다면
                        postSideEffect(LoginSideEffect.NavigateToSandBeach)
                    } else { // 프로필 생성을 안했으면
                        postSideEffect(LoginSideEffect.NavigateToCreateProfile)
                    }
                }
            }
        }
    }

    private fun startKakaoClient() {
        postSideEffect(LoginSideEffect.StartKakaoClient)
    }

}