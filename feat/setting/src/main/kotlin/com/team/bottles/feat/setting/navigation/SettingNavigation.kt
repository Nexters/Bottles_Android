package com.team.bottles.feat.setting.navigation

import SettingNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.setting.account.AccountSettingRoute
import com.team.bottles.feat.setting.notification.NotificationSettingRoute

fun NavGraphBuilder.accountSettingScreen(
    navigateToLoginEndpoint: () -> Unit,
    navigateToMyPage: () -> Unit,
) {
    composable<SettingNavigator.Account>(
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }
    ) {
        AccountSettingRoute(
            navigateToLoginEndpoint = navigateToLoginEndpoint,
            navigateToMyPage = navigateToMyPage,
        )
    }
}

fun NavGraphBuilder.notificationSettingScreen(
    navigateToMyPage: () -> Unit,
) {
    composable<SettingNavigator.Notification>(
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }
    ) {
        NotificationSettingRoute(
            navigateToMyPage = navigateToMyPage
        )
    }
}