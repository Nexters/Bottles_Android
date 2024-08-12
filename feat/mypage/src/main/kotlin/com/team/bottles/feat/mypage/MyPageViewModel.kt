package com.team.bottles.feat.mypage

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.model.Token
import com.team.bottles.core.domain.auth.usecase.DeleteUserUseCase
import com.team.bottles.core.domain.auth.usecase.LogOutUseCase
import com.team.bottles.core.domain.auth.usecase.WebViewConnectUseCase
import com.team.bottles.core.ui.model.AlertType
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageSideEffect
import com.team.bottles.feat.mypage.mvi.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val webViewConnectUseCase: WebViewConnectUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(
    savedStateHandle
) {

    init {
        initialWebConnect()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState =
        MyPageUiState()

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickWebLogOutButton -> reduce { copy(alertType = AlertType.LOG_OUT, showDialog = true) }
            is MyPageIntent.ClickWebDeleteUserButton -> reduce { copy(alertType = AlertType.DELETE_USER, showDialog = true) }
            is MyPageIntent.ClickCancel -> reduce { copy(showDialog = false) }
            is MyPageIntent.ClickDialogLogOutButton -> logOut()
            is MyPageIntent.ClickDialogDeleteUserButton -> deleteUser()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun logOut() {
        launch {
            logOutUseCase()
            reduce { copy(showDialog = false) }
            postSideEffect(MyPageSideEffect.NavigateToLoginEndPoint)
        }
    }

    private fun deleteUser() {
        launch {
            deleteUserUseCase()
            reduce { copy(showDialog = false) }
            postSideEffect(MyPageSideEffect.NavigateToLoginEndPoint)
        }
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