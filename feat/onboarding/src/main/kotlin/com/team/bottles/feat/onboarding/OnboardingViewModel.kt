package com.team.bottles.feat.onboarding

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.onboarding.mvi.OnboardingIntent
import com.team.bottles.feat.onboarding.mvi.OnboardingPage
import com.team.bottles.feat.onboarding.mvi.OnboardingSideEffect
import com.team.bottles.feat.onboarding.mvi.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<OnboardingUiState, OnboardingSideEffect, OnboardingIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): OnboardingUiState =
        OnboardingUiState()

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.ClickNextButton -> nextPage()
            is OnboardingIntent.ClickBackButton -> previousPage()
        }
    }

    private fun nextPage() {
        if (currentState.currentPage.ordinal + 2 > currentState.maxPage) {
            navigateToCreateProfile()
        } else {
            reduce { copy(currentPage = OnboardingPage.entries[currentPage.ordinal + 1]) }
        }
    }

    private fun previousPage() {
        if (currentState.currentPage.ordinal == 0) {
            navigateToLoginEndPoint()
        } else {
            reduce { copy(currentPage = OnboardingPage.entries[currentPage.ordinal - 1]) }
        }
    }

    private fun navigateToCreateProfile() {
        postSideEffect(OnboardingSideEffect.NavigateToCreateProfile)
    }

    private fun navigateToLoginEndPoint() {
        postSideEffect(OnboardingSideEffect.NavigateToLoginEndpoint)
    }
}