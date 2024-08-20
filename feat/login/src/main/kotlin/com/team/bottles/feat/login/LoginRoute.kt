package com.team.bottles.feat.login

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.login.di.clientEntryPoint
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginSideEffect

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToCreateProfile: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity
    val kakaoClient by rememberUpdatedState(newValue = activity.clientEntryPoint.kakaoClient())

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.NavigateToCreateProfile -> navigateToCreateProfile()
                is LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                is LoginSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is LoginSideEffect.StartKakaoClient -> {
                    try {
                        val kakaoClientResult = kakaoClient.login()
                        viewModel.intent(LoginIntent.KakaoLogin(kakaoClientResult = kakaoClientResult))
                    } catch (e: Throwable) {
                        // TODO : 카카오 로그인 취소시 예외 처리
                    }
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}
