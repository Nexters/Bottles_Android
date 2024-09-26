package com.team.bottles.feat.mypage.navigation

import MainNavigator
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.mypage.MyPageRoute

fun NavGraphBuilder.myPageScreen(
    innerPadding: PaddingValues,
    navigateToEditProfile: () -> Unit,
    navigateToSettingNotification: () -> Unit,
    navigateToSettingAccountManagement: () -> Unit,
) {
    composable<MainNavigator.MyPage> {
        MyPageRoute(
            innerPadding = innerPadding,
            navigateToEditProfile = navigateToEditProfile,
            navigateToSettingNotification = navigateToSettingNotification,
            navigateToSettingAccountManagement = navigateToSettingAccountManagement
        )
    }
}
