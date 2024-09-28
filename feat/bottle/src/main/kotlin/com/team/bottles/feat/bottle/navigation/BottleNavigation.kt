package com.team.bottles.feat.bottle.navigation

import ArrivedBottlesNavigator
import MainNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.bottle.arrivedbottles.ArrivedBottlesRoute
import com.team.bottles.feat.bottle.bottlebox.BottleBoxRoute

fun NavGraphBuilder.arrivedBottlesScreen(
    navigateToSandBeach: () -> Unit,
    navigateToBottleBox: () -> Unit
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
            navigateToBottleBox = navigateToBottleBox
        )
    }
}

fun NavGraphBuilder.bottleBoxScreen(
    innerPadding: PaddingValues,
    navigateToPingPong: (Long) -> Unit
) {
    composable<MainNavigator.BottlesBox> {
        BottleBoxRoute(
            innerPadding = innerPadding,
            navigateToPingPong = navigateToPingPong
        )
    }
}
