package com.team.bottles.feat.setting.navigation

import SettingNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.setting.account.AccountSettingRoute
import com.team.bottles.feat.setting.notification.NotificationSettingRoute

fun NavGraphBuilder.accountSettingScreen(
    navigateToLoginEndpoint: () -> Unit,
    navigateToMyPage: () -> Unit,
) {
    composable<SettingNavigator.Account> {
        AccountSettingRoute(
            navigateToLoginEndpoint = navigateToLoginEndpoint,
            navigateToMyPage = navigateToMyPage,
        )
    }
}

fun NavGraphBuilder.notificationSettingScreen(
    navigateToMyPage: () -> Unit,
) {
    composable<SettingNavigator.Notification> {
        NotificationSettingRoute(
            navigateToMyPage = navigateToMyPage
        )
    }
}