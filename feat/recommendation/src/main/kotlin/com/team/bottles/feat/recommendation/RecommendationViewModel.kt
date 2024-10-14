package com.team.bottles.feat.recommendation

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.feat.recommendation.mvi.RecommendationIntent
import com.team.bottles.feat.recommendation.mvi.RecommendationSideEffect
import com.team.bottles.feat.recommendation.mvi.RecommendationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RecommendationUiState, RecommendationSideEffect, RecommendationIntent>(
    savedStateHandle
) {

    init {
        initialWebConnect()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendationUiState = RecommendationUiState()

    override fun handleClientException(throwable: Throwable) { }

    override suspend fun handleIntent(intent: RecommendationIntent) {
        when (intent) {
            is RecommendationIntent.ClickWebCloseButton -> navigateToSandBeach()
            is RecommendationIntent.ClickBottleItem -> navigateToRecommendationDetail(href = intent.href)
        }
    }

    private fun navigateToRecommendationDetail(href: String) {
        postSideEffect(RecommendationSideEffect.NavigateToRecommendationDetail(href = href))
    }

    private fun navigateToSandBeach() {
        postSideEffect(RecommendationSideEffect.NavigateToSandBeach)
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