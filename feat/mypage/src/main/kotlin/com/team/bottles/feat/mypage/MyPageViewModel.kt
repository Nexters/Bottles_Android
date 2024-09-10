package com.team.bottles.feat.mypage

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.GetLatestAppVersionUseCase
import com.team.bottles.core.domain.user.usecase.GetContactsUseCase
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageSideEffect
import com.team.bottles.feat.mypage.mvi.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val getLatestAppVersionUseCase: GetLatestAppVersionUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(
    savedStateHandle
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): MyPageUiState =
        MyPageUiState()

    override suspend fun handleIntent(intent: MyPageIntent) {
        when (intent) {
            is MyPageIntent.ClickEditProfile -> navigateToEditProfile()
            is MyPageIntent.ClickUpdateBlockContact -> checkContactPermission()
            is MyPageIntent.ClickSettingNotification -> navigateToSettingNotification()
            is MyPageIntent.ClickAccountManagement -> navigateToSettingAccountManagement()
            is MyPageIntent.ClickUpdateAppVersion -> navigateToPlayStore()
            is MyPageIntent.ClickAsk -> navigateToKakaoBusinessChannel()
            is MyPageIntent.ClickTermsOfUse -> navigateToTermsOfUseNotion()
            is MyPageIntent.ClickPolicy -> navigateToPolicyNotion()
            is MyPageIntent.ClickConfirmButton -> updateBlockContact()
            is MyPageIntent.CloseDialog -> closeDialog()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun navigateToEditProfile() {
        postSideEffect(MyPageSideEffect.NavigateToEditProfile)
    }

    private fun navigateToSettingNotification() {
        postSideEffect(MyPageSideEffect.NavigateToSettingNotification)
    }

    private fun navigateToSettingAccountManagement() {
        postSideEffect(MyPageSideEffect.NavigateToSettingAccountManagement)
    }

    private fun navigateToKakaoBusinessChannel() {
        postSideEffect(MyPageSideEffect.NavigateToKakaoBusinessChannel)
    }

    private fun navigateToTermsOfUseNotion() {
        postSideEffect(MyPageSideEffect.NavigateToTermsOfUseNotion)
    }

    private fun navigateToPolicyNotion() {
        postSideEffect(MyPageSideEffect.NavigateToPolicyNotion)
    }

    private fun navigateToPlayStore() {
        postSideEffect(MyPageSideEffect.NavigateToPlayStore)
    }

    private fun checkContactPermission() {
        postSideEffect(MyPageSideEffect.CheckContactPermission)
    }

    private fun showBlockContactDialog() {
        reduce { copy(showDialog = true) }
    }

    private fun closeDialog() {
        reduce { copy(showDialog = false) }
    }

    private fun updateBlockContact() {
        // TODO : 해당 연락처를 차단하는 기능 로직
    }

    fun checkAppVersion() {
        launch {
            val latestAppVersionCode = getLatestAppVersionUseCase().toInt()
            val currentAppVersion = currentState.appVersionCode

            if (latestAppVersionCode > currentAppVersion) {
                reduce { copy(canUpdateAppVersion = true) }
            }
        }
    }

    fun fetchContacts() {
        launch {
            val contacts = getContactsUseCase()
            reduce { copy(inDeviceContacts = contacts.size) }
            showBlockContactDialog()
        }
    }

}
