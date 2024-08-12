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
    navigateToSignup: () -> Unit,
    navigateToSmsLogin: () -> Unit,
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
                is LoginSideEffect.NavigateToSignup -> navigateToSignup()
                is LoginSideEffect.NavigateToSmsLogin -> navigateToSmsLogin()
                is LoginSideEffect.StartKakaoClient -> {
                    val kakaoClientResult = kakaoClient.login()
                    viewModel.intent(LoginIntent.KakaoLogin(kakaoClientResult = kakaoClientResult))
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}
