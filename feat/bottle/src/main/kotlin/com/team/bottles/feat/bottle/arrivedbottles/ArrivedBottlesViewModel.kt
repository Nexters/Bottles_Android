package com.team.bottles.feat.bottle.arrivedbottles

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesIntent
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesSideEffect
import com.team.bottles.feat.bottle.arrivedbottles.mvi.ArrivedBottlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArrivedBottlesViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ArrivedBottlesUiState, ArrivedBottlesSideEffect, ArrivedBottlesIntent>(
    savedStateHandle
) {

    init {
        initialWebConnect()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): ArrivedBottlesUiState =
        ArrivedBottlesUiState()

    override suspend fun handleIntent(intent: ArrivedBottlesIntent) {
        when (intent) {
            is ArrivedBottlesIntent.ClickWebCloseButton -> navigateToSandBeach()
            is ArrivedBottlesIntent.ClickWebBottleAcceptButton -> navigateToBottleBox()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToSandBeach() {
        postSideEffect(ArrivedBottlesSideEffect.NavigateToSandBeach)
    }

    private fun navigateToBottleBox() {
        postSideEffect(ArrivedBottlesSideEffect.NavigateToBottleBox)
    }

    private fun initialWebConnect() {
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

}