package com.team.bottles.feat.onboarding

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.onboarding.mvi.OnboardingIntent
import com.team.bottles.feat.onboarding.mvi.OnboardingSideEffect
import com.team.bottles.feat.onboarding.mvi.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<OnboardingUiState, OnboardingSideEffect, OnboardingIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): OnboardingUiState =
        OnboardingUiState(page = 0)

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.ClickNextButton -> {
                if (currentState.page > 1) navigateToCreateProfile()
                else reduce { copy(page = page + 1) }
            }
        }
    }

    private fun navigateToCreateProfile() {
        postSideEffect(OnboardingSideEffect.NavigateToCreateProfile)
    }
}