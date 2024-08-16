package com.team.bottles.feat.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenStatus: WebViewConnectUseCase
): ViewModel() {

    private val _sideEffect: MutableSharedFlow<SplashSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            val tokens = getTokenStatus.getLocalToken()

            delay(1000L)

            if (tokens.accessToken.isEmpty()) {
                _sideEffect.emit(SplashSideEffect.NavigateToLoginEndpoint)
            } else {
                _sideEffect.emit(SplashSideEffect.NavigateToSandBeach)
            }
        }
    }

}