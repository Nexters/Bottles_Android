package com.team.bottles.feat.setting.notification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.setting.SettingViewModel
import com.team.bottles.feat.setting.mvi.SettingSideEffect
import kotlinx.coroutines.flow.collect

@Composable
internal fun NotificationSettingRoute(
    viewModel: SettingViewModel = hiltViewModel(),
    navigateToMyPage: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            if (sideEffect is SettingSideEffect.NavigateToMyPage) {
                navigateToMyPage()
            }
        }
    }

    NotificationSettingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}