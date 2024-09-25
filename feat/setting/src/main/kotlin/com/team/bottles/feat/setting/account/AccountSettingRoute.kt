package com.team.bottles.feat.setting.account

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.setting.account.mvi.AccountSettingSideEffect

@Composable
internal fun AccountSettingRoute(
    viewModel: AccountSettingViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToMyPage: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AccountSettingSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
                is AccountSettingSideEffect.NavigateToMyPage -> navigateToMyPage()
                is AccountSettingSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    AccountSettingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}