package com.team.bottles.feat.profile.edit

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.profile.edit.mvi.EditProfileSideEffect

@Composable
internal fun EditProfileRoute(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navigateToMyPage: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is EditProfileSideEffect.NavigateToMyPage -> navigateToMyPage()
                is EditProfileSideEffect.ShowToastMessage -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    EditProfileScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}