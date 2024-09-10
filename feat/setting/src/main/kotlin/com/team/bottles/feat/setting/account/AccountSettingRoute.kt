package com.team.bottles.feat.setting.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.setting.SettingViewModel
import com.team.bottles.feat.setting.mvi.SettingSideEffect

@Composable
internal fun AccountSettingRoute(
    viewModel: SettingViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToMyPage: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SettingSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
                is SettingSideEffect.NavigateToMyPage -> navigateToMyPage()
            }
        }
    }

    AccountSettingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}