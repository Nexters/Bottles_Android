package com.team.bottles.feat.signup

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.signup.mvi.SignupIntent
import com.team.bottles.feat.signup.mvi.SignupSideEffect
import com.team.bottles.feat.signup.mvi.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle
) : BaseViewModel<SignupUiState, SignupSideEffect, SignupIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignupUiState =
        SignupUiState

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SignupIntent) {
        TODO("Not yet implemented")
    }


}