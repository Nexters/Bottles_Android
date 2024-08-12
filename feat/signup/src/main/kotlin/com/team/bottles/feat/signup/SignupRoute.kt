package com.team.bottles.feat.signup

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.signup.mvi.SignupSideEffect

@Composable
fun SignupRoute(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignupSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is SignupSideEffect.NavigateToLoginEndPoint -> navigateToLoginEndpoint()
                is SignupSideEffect.OpenLink -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sideEffect.href))
                    context.startActivity(intent)
                }
            }
        }
    }

    SignupScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}
