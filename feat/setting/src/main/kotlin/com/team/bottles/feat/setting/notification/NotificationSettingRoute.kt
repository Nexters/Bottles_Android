package com.team.bottles.feat.setting.notification

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.setting.notification.mvi.NotificationSideEffect

@Composable
internal fun NotificationSettingRoute(
    viewModel: NotificationSettingViewModel = hiltViewModel(),
    navigateToMyPage: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is NotificationSideEffect.NavigateToMyPage -> navigateToMyPage()
                is NotificationSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    NotificationSettingScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}