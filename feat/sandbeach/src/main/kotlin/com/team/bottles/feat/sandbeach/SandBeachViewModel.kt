package com.team.bottles.feat.sandbeach

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.GetRequiredAppVersionUseCase
import com.team.bottles.core.domain.bottle.usecase.GetBottleListUseCase
import com.team.bottles.core.domain.bottle.usecase.GetPingPongListUseCase
import com.team.bottles.core.domain.profile.model.UserProfileStatus
import com.team.bottles.core.domain.profile.usecase.GetUserProfileStatusUseCase
import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.BottlesNetworkException
import com.team.bottles.feat.sandbeach.mvi.BottleStatus
import com.team.bottles.feat.sandbeach.mvi.SandBeachIntent
import com.team.bottles.feat.sandbeach.mvi.SandBeachSideEffect
import com.team.bottles.feat.sandbeach.mvi.SandBeachUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SandBeachViewModel @Inject constructor(
    private val getUserProfileStatusUseCase: GetUserProfileStatusUseCase,
    private val getBottleListUseCase: GetBottleListUseCase,
    private val getPingPongListUseCase: GetPingPongListUseCase,
    private val getRequiredAppVersionUseCase: GetRequiredAppVersionUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SandBeachUiState, SandBeachSideEffect, SandBeachIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SandBeachUiState =
        SandBeachUiState()

    override fun handleClientException(throwable: Throwable) {
        when (throwable) {
            is BottlesException -> showErrorMessage(throwable.message?: "")
            is BottlesNetworkException -> {
                showErrorMessage(throwable.message?: "")
                showErrorScreen()
            }
            else -> showErrorScreen()
        }
    }

    private fun retry() {
        closeErrorScreen()
        initSandBeach()
    }

    private fun showErrorMessage(message: String) {
        postSideEffect(SandBeachSideEffect.ShowErrorMessage(message = message))
    }

    private fun showErrorScreen() {
        reduce { copy(isError = true) }
    }

    private fun closeErrorScreen() {
        reduce { copy(isError = false) }
    }

    private suspend fun checkRequiredUpdate() {
        val requiredAppVersion = getRequiredAppVersionUseCase()

        if (requiredAppVersion > currentState.appVersionCode) {
            reduce { copy(showDialog = true) }
        }
    }

    fun initSandBeach() {
        launch {
            checkRequiredUpdate()

            val userProfileStatus = getUserProfileStatusUseCase()

            when (userProfileStatus) {
                UserProfileStatus.EMPTY,
                UserProfileStatus.ONLY_PROFILE_CREATED,
                UserProfileStatus.INTRODUCE_DONE -> {
                    reduce { copy(bottleStatus = BottleStatus.REQUIRE_INTRODUCTION) }
                }
                UserProfileStatus.PHOTO_DONE -> {
                    val arrivedBottles = getBottleListUseCase()

                    if (arrivedBottles.sentBottles.isNotEmpty() || arrivedBottles.randomBottles.isNotEmpty()) {
                        reduce {
                            copy(
                                bottleStatus = BottleStatus.IN_ARRIVED_BOTTLE,
                                newBottleValue = arrivedBottles.randomBottles.size + arrivedBottles.sentBottles.size
                            )
                        }
                        return@launch
                    } else {
                        val activeBottles = getPingPongListUseCase().activeBottles

                        if (activeBottles.isNotEmpty()) {
                            reduce {
                                copy(
                                    bottleStatus = BottleStatus.IN_BOTTLE_BOX,
                                    bottleBoxValue = activeBottles.size
                                )
                            }
                            return@launch
                        } else {
                            reduce {
                                copy(
                                    bottleStatus = BottleStatus.NONE_BOTTLE,
                                    afterArrivedTime = arrivedBottles.nextBottleLeftHours
                                )
                            }
                            return@launch
                        }
                    }
                }
            }
        }
    }

    override suspend fun handleIntent(intent: SandBeachIntent) {
        when (intent) {
            is SandBeachIntent.ClickCreateIntroductionButton -> navigateToIntroduction()
            is SandBeachIntent.ClickSandBeach -> onClickSandBeach()
            is SandBeachIntent.ClickConfirmButton -> navigateToPlayStore()
            is SandBeachIntent.ClickRetryButton -> retry()
        }
    }

    private fun onClickSandBeach() {
        when (currentState.bottleStatus) {
            BottleStatus.REQUIRE_INTRODUCTION -> {}
            BottleStatus.NONE_BOTTLE -> navigateToArrivedBottle()
            BottleStatus.IN_BOTTLE_BOX -> navigateToBottleBox()
            BottleStatus.IN_ARRIVED_BOTTLE -> navigateToArrivedBottle()
        }
    }

    private fun navigateToIntroduction() {
        postSideEffect(SandBeachSideEffect.NavigateToIntroduction)
    }

    private fun navigateToBottleBox() {
        postSideEffect(SandBeachSideEffect.NavigateToBottleBox)
    }

    private fun navigateToArrivedBottle() {
        postSideEffect(SandBeachSideEffect.NavigateToArrivedBottle)
    }

    private fun navigateToPlayStore() {
        postSideEffect(SandBeachSideEffect.NavigateToPlayStore)
    }

}