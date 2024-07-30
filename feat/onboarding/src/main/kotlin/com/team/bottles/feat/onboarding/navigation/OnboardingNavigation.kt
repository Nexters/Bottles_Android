package com.team.bottles.feat.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import OnboardingNavigator
import com.team.bottles.feat.onboarding.OnboardingRoute

fun NavGraphBuilder.onboardingScreen(
    navigateToCreateProfile: () -> Unit
) {
    composable<OnboardingNavigator> {
        OnboardingRoute(navigateToCreateProfile = navigateToCreateProfile)
    }
}
