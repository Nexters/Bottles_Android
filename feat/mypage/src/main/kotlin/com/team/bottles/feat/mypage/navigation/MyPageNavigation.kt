package com.team.bottles.feat.mypage.navigation

import MainNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
    composable<MainNavigator.MyPage>(
        enterTransition = {
            when (initialState.destination.route) {
                "ProfileNavigator.Edit",
                "SettingNavigator.Account",
                "SettingNavigator.Notification"-> {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
                else -> null
            }
        }
    ) {
        MyPageRoute(
            innerPadding = innerPadding,
            navigateToEditProfile = navigateToEditProfile,
            navigateToSettingNotification = navigateToSettingNotification,
            navigateToSettingAccountManagement = navigateToSettingAccountManagement
        )
    }
}
