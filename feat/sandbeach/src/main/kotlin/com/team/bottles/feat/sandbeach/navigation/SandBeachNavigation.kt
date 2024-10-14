package com.team.bottles.feat.sandbeach.navigation

import MainNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.sandbeach.SandBeachRoute

fun NavGraphBuilder.sandBeachScreen(
    innerPadding: PaddingValues,
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
    navigateToPingPong: () -> Unit,
) {
    composable<MainNavigator.SandBeach>(
        enterTransition = {
            when (initialState.destination.route) {
                "ArrivedBottlesNavigator",
                "ProfileNavigator.Introduction" -> {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
                else -> null
            }
        }
    ) {
        SandBeachRoute(
            innerPadding = innerPadding,
            navigateToIntroduction = navigateToIntroduction,
            navigateToArrivedBottles = navigateToArrivedBottles,
            navigateToPingPong = navigateToPingPong
        )
    }
}
