package com.team.bottles.feat.profile.createprofile

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileIntent
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileSideEffect
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<CreateProfileUiState, CreateProfileSideEffect, CreateProfileIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): CreateProfileUiState =
        CreateProfileUiState()


    override suspend fun handleIntent(intent: CreateProfileIntent) {
        when(intent) {
            is CreateProfileIntent.ClickNextButton -> navigateToMain()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToMain() {
        postSideEffect(CreateProfileSideEffect.NavigateToMain)
    }

}