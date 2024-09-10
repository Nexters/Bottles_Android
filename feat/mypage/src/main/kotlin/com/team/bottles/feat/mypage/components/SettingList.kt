package com.team.bottles.feat.mypage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.cards.BottlesCard
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItem
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithArrow
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithButton
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun SettingList(
    modifier: Modifier = Modifier,
    onClickEditProfile: () -> Unit,
    onClickUpdateBlockContact: () -> Unit,
    onClickSettingNotification: () -> Unit,
    onClickAccountManagement: () -> Unit,
    onClickUpdateAppVersion: () -> Unit,
    onClickAsk: () -> Unit,
    onClickTermsOfUse: () -> Unit,
    onClickPolicy: () -> Unit,
    blockedUserValue: Int,
    appVersion: String,
    canUpdateAppVersion: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        /*BottlesCard {
            BottlesSettingItemWithArrow(
                title = "프로필 수정",
                onClickItem = onClickEditProfile
            )
        }*/ // TODO : 웹뷰 작업 완료시 기능 추가

        BottlesCard(
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.large
            )
        ) {
            BottlesSettingItemWithButton(
                title = "연락처 차단",
                subTitle = "연락처 속 ${blockedUserValue}명을 차단햇어요",
                onClickButton = onClickUpdateBlockContact,
                buttonText = "업데이트"
            )

            BottlesSettingItemWithArrow(
                title = "알림 설정",
                onClickItem = onClickSettingNotification
            )

            BottlesSettingItemWithArrow(
                title = "계정 관리",
                onClickItem = onClickAccountManagement
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = BottlesTheme.color.border.secondary
            )

            if (canUpdateAppVersion) {
                BottlesSettingItemWithButton(
                    title = "앱 버전",
                    subTitle = appVersion,
                    onClickButton = onClickUpdateAppVersion,
                    buttonText = "업데이트"
                )
            } else {
                BottlesSettingItem(
                    title = "앱 버전",
                    subTitle = appVersion
                )
            }

            BottlesSettingItemWithArrow(
                title = "1:1 문의",
                onClickItem = onClickAsk
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = BottlesTheme.color.border.secondary
            )

            BottlesSettingItemWithArrow(
                title = "보틀 이용약관",
                onClickItem = onClickTermsOfUse
            )

            BottlesSettingItemWithArrow(
                title = "개인정보처리방침",
                onClickItem = onClickPolicy
            )
        }
    }
}

@Preview
@Composable
private fun SettingListPreview() {
    BottlesTheme {
        SettingList(
            onClickEditProfile = { /*TODO*/ },
            onClickUpdateBlockContact = { /*TODO*/ },
            onClickSettingNotification = { /*TODO*/ },
            onClickAccountManagement = { /*TODO*/ },
            onClickUpdateAppVersion = { /*TODO*/ },
            onClickAsk = { /*TODO*/ },
            onClickTermsOfUse = { /*TODO*/ },
            onClickPolicy = { /*TODO*/ },
            blockedUserValue = 5,
            appVersion = "1.0.0",
            canUpdateAppVersion = false
        )
    }
}