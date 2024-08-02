package com.team.bottles.feat.login.smslogin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginSideEffect

@Composable
fun SmsLoginRoute(
    viewModel: SmsLoginViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SmsLoginSideEffect.NavigateToLogin -> navigateToLogin()
                is SmsLoginSideEffect.NavigateToSandBeach -> navigateToSandBeach()
            }
        }
    }

    SmsLoginScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}
