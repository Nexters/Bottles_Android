package com.team.bottles.feat.bottle.navigation

import ArrivedBottlesNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.bottle.arrivedbottles.ArrivedBottlesRoute

fun NavGraphBuilder.arrivedBottlesScreen(
    navigateToSandBeach: () -> Unit,
    navigateToPingPong: () -> Unit
) {
    composable<ArrivedBottlesNavigator>(
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
        ArrivedBottlesRoute(
            navigateToSandBeach = navigateToSandBeach,
            navigateToPingPong = navigateToPingPong
        )
    }
}
