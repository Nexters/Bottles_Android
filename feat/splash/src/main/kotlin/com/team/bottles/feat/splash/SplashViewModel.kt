package com.team.bottles.feat.splash

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.GetRequiredAppVersionUseCase
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.core.domain.profile.model.UserProfileStatus
import com.team.bottles.core.domain.profile.usecase.GetUserProfileStatusUseCase
import com.team.bottles.feat.splash.mvi.SplashIntent
import com.team.bottles.feat.splash.mvi.SplashSideEffect
import com.team.bottles.feat.splash.mvi.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenStatus: WebViewConnectUseCase,
    private val getUserProfileStatusUseCase: GetUserProfileStatusUseCase,
    private val getRequiredAppVersionUseCase: GetRequiredAppVersionUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel<SplashUiState, SplashSideEffect, SplashIntent>(savedStateHandle) {

    init {
        launch {
            val requiredAppVersion = getRequiredAppVersionUseCase()

            if (requiredAppVersion > currentState.appVersionCode) {
                reduce { copy(showDialog = true) }
            } else {
                val tokens = getTokenStatus.getLocalToken()

                delay(1000L)

                if (tokens.accessToken.isEmpty()) {
                    postSideEffect(SplashSideEffect.NavigateToLoginEndpoint)
                } else {
                    val profileStatus = getUserProfileStatusUseCase()

                    when (profileStatus) {
                        UserProfileStatus.EMPTY -> postSideEffect(SplashSideEffect.NavigateToOnboarding)
                        else -> postSideEffect(SplashSideEffect.NavigateToSandBeach)
                    }
                }
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SplashUiState =
        SplashUiState()

    override suspend fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.ClickConfirmButton -> postSideEffect(SplashSideEffect.NavigateToPlayStore)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

}