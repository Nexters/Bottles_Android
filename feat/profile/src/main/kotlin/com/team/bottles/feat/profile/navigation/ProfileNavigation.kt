package com.team.bottles.feat.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ProfileNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import com.team.bottles.feat.profile.createprofile.CreateProfileRoute
import com.team.bottles.feat.profile.edit.EditProfileRoute
import com.team.bottles.feat.profile.introduction.IntroductionRoute

fun NavGraphBuilder.createProfileScreen(
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    composable<ProfileNavigator.CreateProfile> {
        CreateProfileRoute(
            navigateToSandBeach = navigateToSandBeach,
            navigateToOnboarding = navigateToOnboarding
        )
    }
}

fun NavGraphBuilder.introductionScreen(
    navigateToSandBeach: () -> Unit
) {
    composable<ProfileNavigator.Introduction>(
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
        IntroductionRoute(navigateToSandBeach = navigateToSandBeach)
    }
}

fun NavGraphBuilder.editProfileScreen(
    navigateToMyPage: () -> Unit
) {
    composable<ProfileNavigator.Edit>(
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
        EditProfileRoute(
            navigateToMyPage = navigateToMyPage
        )
    }
}