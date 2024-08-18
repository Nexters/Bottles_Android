package com.team.bottles.feat.splash

import SplashNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.splashScreen(
    navigateToLoginEndpoint: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    composable<SplashNavigator> {
        SplashRoute(
            navigateToLoginEndpoint = navigateToLoginEndpoint,
            navigateToSandBeach = navigateToSandBeach,
            navigateToOnboarding = navigateToOnboarding
        )
    }
}
