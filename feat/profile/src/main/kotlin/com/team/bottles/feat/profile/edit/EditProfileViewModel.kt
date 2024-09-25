package com.team.bottles.feat.profile.edit

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.profile.edit.mvi.EditProfileIntent
import com.team.bottles.feat.profile.edit.mvi.EditProfileSideEffect
import com.team.bottles.feat.profile.edit.mvi.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EditProfileViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<EditProfileUiState, EditProfileSideEffect, EditProfileIntent>(
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

    override fun createInitialState(savedStateHandle: SavedStateHandle): EditProfileUiState =
        EditProfileUiState()

    override suspend fun handleIntent(intent: EditProfileIntent) {
        when (intent) {
            is EditProfileIntent.ClickWebCloseButton -> navigateToMyPage()
        }
    }

    override fun handleClientException(throwable: Throwable) {

    }

    private fun navigateToMyPage() {
        postSideEffect(EditProfileSideEffect.NavigateToMyPage)
    }

}