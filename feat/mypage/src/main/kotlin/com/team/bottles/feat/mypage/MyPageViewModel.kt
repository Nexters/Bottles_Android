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

            reduce {
                copy(
                    imageUrl = profile.imageUrl,
                    userName = profile.userName,
                    userAge = profile.age,
                    blockedUserValue = profile.blockedUserCount
                )
            }
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
            is MyPageIntent.ClickConfirmBlockContacts -> updateBlockContact()
            is MyPageIntent.CloseBlockContactsDialog -> closeBlockContactsDialog()
            is MyPageIntent.ClickConfirmContactAccessButton -> {
                navigateToSystemSetting()
                closeAccessPermissionGuideDialog()
            }
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
        reduce { copy(showBlockContactsDialog = true) }
    }

    private fun closeBlockContactsDialog() {
        reduce { copy(showBlockContactsDialog = false) }
    }

    private fun closeAccessPermissionGuideDialog() {
        reduce { copy(showAccessPermissionGuideDialog = false) }
    }

    private fun navigateToSystemSetting() {
        postSideEffect(MyPageSideEffect.NavigateToSystemSetting)
    }

    private fun updateBlockContact() {
        launch {
            updateBlockingContactsUseCase(contacts = currentState.inDeviceContacts)
            val profile = getUserProfileUseCase()

            reduce {
                copy(showBlockContactsDialog = false, blockedUserValue = profile.blockedUserCount)
            }
            postSideEffect(MyPageSideEffect.CompleteBlockContacts)
        }
    }

    fun checkAppVersion() {
        launch {
            val latestAppVersionCode = getLatestAppVersionUseCase()
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

    fun showAccessPermissionGuideDialog() {
        reduce { copy(showAccessPermissionGuideDialog = true) }
    }

}
