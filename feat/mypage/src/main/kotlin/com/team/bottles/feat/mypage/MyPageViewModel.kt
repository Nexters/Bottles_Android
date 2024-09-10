package com.team.bottles.feat.mypage

import androidx.lifecycle.SavedStateHandle
import com.team.bottles.core.common.BaseViewModel
import com.team.bottles.core.domain.auth.usecase.GetLatestAppVersionUseCase
import com.team.bottles.core.domain.profile.usecase.GetUserProfileUseCase
import com.team.bottles.core.domain.user.usecase.GetContactsUseCase
import com.team.bottles.core.domain.user.usecase.UpdateBlockingContactsUseCase
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageSideEffect
import com.team.bottles.feat.mypage.mvi.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val getLatestAppVersionUseCase: GetLatestAppVersionUseCase,
    private val updateBlockingContactsUseCase: UpdateBlockingContactsUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageIntent>(
    savedStateHandle
) {

    init {
        launch {
            val profile = getUserProfileUseCase()
            val userImageUrl = profile.imageUrl
            val userName = profile.userName
            val userAge = profile.age
            reduce { copy(imageUrl = userImageUrl, userName = userName, userAge = userAge) }
        }
    }

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
        launch {
            updateBlockingContactsUseCase(contacts = currentState.inDeviceContacts)
            // TODO : 차단한 연락처 갯수 얻는 API 호출
            reduce { copy(showDialog = false) }
        }
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
            reduce { copy(inDeviceContacts = contacts) }
            showBlockContactDialog()
        }
    }

}
