package com.team.bottles.feat.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.signup.mvi.SignupSideEffect

@Composable
fun SignupRoute(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignupSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is SignupSideEffect.NavigateToLogin -> navigateToLogin()
            }
        }
    }

    SignupScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}
