package com.team.bottles.feat.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ProfileNavigator
import com.team.bottles.feat.profile.createprofile.CreateProfileRoute
import com.team.bottles.feat.profile.introduction.IntroductionRoute

fun NavGraphBuilder.createProfileScreen(
    navigateToSandBeach: () -> Unit
) {
    composable<ProfileNavigator.CreateProfile> {
        CreateProfileRoute(navigateToSandBeach = navigateToSandBeach)
    }
}

fun NavGraphBuilder.introductionScreen(
    navigateToSandBeach: () -> Unit
) {
    composable<ProfileNavigator.Introduction> {
        IntroductionRoute(navigateToSandBeach = navigateToSandBeach)
    }
}
