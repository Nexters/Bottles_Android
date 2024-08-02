package com.team.bottles.feat.login.smslogin

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginIntent
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginSideEffect
import com.team.bottles.feat.login.smslogin.mvi.SmsLoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmsLoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SmsLoginUiState, SmsLoginSideEffect, SmsLoginIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SmsLoginUiState =
        SmsLoginUiState

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: SmsLoginIntent) {
        TODO("Not yet implemented")
    }

}