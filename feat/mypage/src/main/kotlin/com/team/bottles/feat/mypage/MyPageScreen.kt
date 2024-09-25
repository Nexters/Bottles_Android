package com.team.bottles.feat.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.etc.BottlesUserInfo
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertConfirmDialog
import com.team.bottles.core.ui.BottlesAlertDialogLeftDismissRightConfirm
import com.team.bottles.core.ui.BottlesErrorScreen
import com.team.bottles.feat.mypage.components.SettingList
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageUiState

@Composable
internal fun MyPageScreen(
    innerPadding: PaddingValues,
    uiState: MyPageUiState,
    onIntent: (MyPageIntent) -> Unit
) {
    val scrollState = rememberScrollState()

    if (uiState.showAccessPermissionGuideDialog) {
        BottlesAlertConfirmDialog(
            onClose = { /* 닫을 수 없는 다이얼 로그 */ },
            onConfirm = { onIntent(MyPageIntent.ClickConfirmContactAccessButton) },
            confirmButtonText = "설정하러 가기",
            title = "연락처 접근 권한 안내",
            content = "설정 > 권한에서\n연락처 접근을 허락해주세요."
        )
    }

    if (uiState.showBlockContactsDialog) {
        BottlesAlertDialogLeftDismissRightConfirm(
            onClose = { onIntent(MyPageIntent.CloseBlockContactsDialog) },
            onDismiss = { onIntent(MyPageIntent.CloseBlockContactsDialog) },
            onConfirm = { onIntent(MyPageIntent.ClickConfirmBlockContacts) },
            confirmButtonText = "차단하기",
            dismissButtonText = "취소하기",
            title = "연락처 차단",
            content = "주소록에 있는 ${uiState.inDeviceContacts.size}개의\n"
                    + "전화번호를 차단할까요?"
        )
    }

    if (uiState.isError) {
        BottlesErrorScreen(
            modifier = Modifier.systemBarsPadding(),
            onClickBackButton = { },
            onClickRetryButton = { onIntent(MyPageIntent.ClickRetry) }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .systemBarsPadding()
                .verticalScroll(state = scrollState)
        ) {
            BottlesTopBar()

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

            BottlesUserInfo(
                modifier = Modifier.padding(horizontal = 32.dp),
                imageUrl = uiState.imageUrl,
                userName = uiState.userName,
                userAge = uiState.userAge,
                isBlur = false
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

            SettingList(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClickEditProfile = { onIntent(MyPageIntent.ClickEditProfile) },
                onClickUpdateBlockContact = { onIntent(MyPageIntent.ClickUpdateBlockContact) },
                onClickSettingNotification = { onIntent(MyPageIntent.ClickSettingNotification) },
                onClickAccountManagement = { onIntent(MyPageIntent.ClickAccountManagement) },
                onClickUpdateAppVersion = { onIntent(MyPageIntent.ClickUpdateAppVersion) },
                onClickAsk = { onIntent(MyPageIntent.ClickAsk) },
                onClickTermsOfUse = { onIntent(MyPageIntent.ClickTermsOfUse) },
                onClickPolicy = { onIntent(MyPageIntent.ClickPolicy) },
                blockedUserValue = uiState.blockedUserValue,
                appVersion = uiState.appVersionName,
                canUpdateAppVersion = uiState.canUpdateAppVersion
            )

            Spacer(modifier = Modifier.height(height = 24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    BottlesTheme {
        MyPageScreen(
            uiState = MyPageUiState(
                userName = "뇽뇽이",
                userAge = 15,
                appVersionName = "1.0.0"
            ),
            onIntent = {},
            innerPadding = PaddingValues()
        )
    }
}