package com.team.bottles.feat.mypage

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageSideEffect
import com.team.bottles.feat.mypage.mvi.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState =
        MyPageUiState()


    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickLogOutButton -> logOut()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun logOut() {
        postSideEffect(MyPageSideEffect.LogOut)
    }

}