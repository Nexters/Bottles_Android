package com.team.bottles.feat.setting.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.setting.account.mvi.AccountSettingSideEffect
import com.team.bottles.feat.setting.notification.mvi.NotificationSideEffect

@Composable
internal fun AccountSettingRoute(
    viewModel: AccountSettingViewModel = hiltViewModel(),
    navigateToLoginEndpoint: () -> Unit,
    navigateToMyPage: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AccountSettingSideEffect.NavigateToLoginEndpoint -> navigateToLoginEndpoint()
                is AccountSettingSideEffect.NavigateToMyPage -> navigateToMyPage()
            }
        }
    }

    AccountSettingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}