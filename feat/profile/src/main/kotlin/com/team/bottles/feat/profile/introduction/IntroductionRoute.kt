package com.team.bottles.feat.profile.introduction

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.profile.introduction.mvi.IntroductionSideEffect

@Composable
internal fun IntroductionRoute(
    viewModel: IntroductionViewModel = hiltViewModel(),
    navigateToSandBeach: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is IntroductionSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is IntroductionSideEffect.CompleteIntroduction -> {
                    Toast.makeText(context, sideEffect.toastMessage, Toast.LENGTH_SHORT).show()
                    navigateToSandBeach()
                }
                is IntroductionSideEffect.RequireSelectPhoto ->
                    Toast.makeText(context, sideEffect.toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    IntroductionScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )

}