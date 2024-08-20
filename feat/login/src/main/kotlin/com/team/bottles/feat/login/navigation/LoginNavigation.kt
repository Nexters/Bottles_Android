package com.team.bottles.feat.login.navigation

import LoginNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.login.LoginRoute

fun NavGraphBuilder.loginScreen(
    navigateToOnboarding: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToCreateProfile: () -> Unit
) {
    composable<LoginNavigator.Endpoint> {
        LoginRoute(
            navigateToOnboarding = navigateToOnboarding,
            navigateToSandBeach = navigateToSandBeach,
            navigateToCreateProfile = navigateToCreateProfile
        )
    }
}
