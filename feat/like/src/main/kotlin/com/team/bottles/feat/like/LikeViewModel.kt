package com.team.bottles.feat.like

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.like.mvi.LikeIntent
import com.team.bottles.feat.like.mvi.LikeSideEffect
import com.team.bottles.feat.like.mvi.LikeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<LikeUiState, LikeSideEffect, LikeIntent>(
    savedStateHandle
) {

    init {
        initialWebConnect()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): LikeUiState =
        LikeUiState()

    override suspend fun handleIntent(intent: LikeIntent) {
        when (intent) {
            is LikeIntent.ClickBottleItem -> navigateToLikeDetail()
        }
    }

    override fun handleClientException(throwable: Throwable) { } // TODO : 예외 처리

    private fun navigateToLikeDetail() {
        postSideEffect(LikeSideEffect.NavigateToLikeDetail)
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