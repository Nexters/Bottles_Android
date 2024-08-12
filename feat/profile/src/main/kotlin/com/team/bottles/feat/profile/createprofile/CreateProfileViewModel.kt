package com.team.bottles.feat.profile.createprofile

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileIntent
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileSideEffect
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CreateProfileUiState, CreateProfileSideEffect, CreateProfileIntent>(
    savedStateHandle
) {
    init {
        launch {
            webViewConnectUseCase.getLocalToken().run {
                reduce {
                    copy(
                        token = Token(
                            accessToken = accessToken,
                            refreshToken = refreshToken
                        )
                    )
                }
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): CreateProfileUiState =
        CreateProfileUiState()

    override suspend fun handleIntent(intent: CreateProfileIntent) {
        when (intent) {
            is CreateProfileIntent.ClickWebCreateProfileButton -> createProfile()
            is CreateProfileIntent.ClickWebCloseButton -> navigateToOnboarding()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun createProfile() {
        postSideEffect(CreateProfileSideEffect.NavigateToMain)
    }

    private fun navigateToOnboarding() {
        postSideEffect(CreateProfileSideEffect.NavigateToOnboarding)
    }

}