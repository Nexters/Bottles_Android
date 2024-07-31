package com.team.bottles.feat.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import LoginNavigator
import com.team.bottles.feat.login.LoginRoute

fun NavGraphBuilder.loginScreen(
    navigateToOnboarding: () -> Unit
) {
    composable<LoginNavigator> {
        LoginRoute(navigateToOnboarding = navigateToOnboarding)
    }
}
