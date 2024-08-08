package com.team.bottles.feat.bottle.navigation

import ArrivedBottlesNavigator
import MainNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.bottle.arrivedbottles.ArrivedBottlesRoute
import com.team.bottles.feat.bottle.bottlebox.BottleBoxRoute

fun NavGraphBuilder.arrivedBottlesScreen(
    navigateToSandBeach: () -> Unit
) {
    composable<ArrivedBottlesNavigator> {
        ArrivedBottlesRoute(navigateToSandBeach = navigateToSandBeach)
    }
}

fun NavGraphBuilder.bottleBoxScreen(
    navigateToPingPong: (Long) -> Unit
) {
    composable<MainNavigator.BottlesBox> {
        BottleBoxRoute(navigateToPingPong = navigateToPingPong)
    }
}
