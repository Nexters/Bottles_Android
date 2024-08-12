package com.team.bottles.feat.mypage.navigation

import MainNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.mypage.MyPageRoute

fun NavGraphBuilder.myPageScreen(
    navigateToLoginEndPoint: () -> Unit
) {
    composable<MainNavigator.MyPage> {
        MyPageRoute(navigateToLoginEndPoint = navigateToLoginEndPoint)
    }
}
