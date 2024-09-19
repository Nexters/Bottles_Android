package com.team.bottles.feat.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.components.cards.BottlesCard
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithArrow
import com.team.bottles.core.designsystem.components.lists.BottlesSettingItemWithToggleButton
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun AccountSetting(
    modifier: Modifier = Modifier,
    isMatchingActive: Boolean,
    onChangeMatchingActive: () -> Unit,
    onClickLogOut: () -> Unit,
    onClickDeleteUser: () -> Unit,
) {
    BottlesCard(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.large
        )
    ) {
//        BottlesSettingItemWithToggleButton(
//            title = "매칭 활성화",
//            subTitle = "비활성화 시 다른 사람을 추천 받을 수 없고\n" +
//                    "회원님도 다른 사람에게 추천되지 않아요",
//            checked = isMatchingActive,
//            onCheckedChange = onChangeMatchingActive
//        ) TODO : 매칭 활성화 API 구현시 UI 수정

        BottlesSettingItemWithArrow(
            title = "로그아웃",
            onClickItem = onClickLogOut
        )

        BottlesSettingItemWithArrow(
            title = "탈퇴하기",
            onClickItem = onClickDeleteUser
        )
    }
}

@Preview
@Composable
private fun AccountSettingPreview() {
    BottlesTheme {
        AccountSetting(
            isMatchingActive = true,
            onChangeMatchingActive = {},
            onClickDeleteUser = {},
            onClickLogOut = {}
        )
    }
}