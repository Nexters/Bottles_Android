package com.team.bottles.feat.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.login.mvi.LoginSideEffect

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}
