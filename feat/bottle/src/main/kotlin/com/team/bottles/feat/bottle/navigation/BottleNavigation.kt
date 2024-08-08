package com.team.bottles.feat.bottle.navigation

import BottleNavigator
import MainNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.team.bottles.feat.bottle.BottleRoute
import com.team.bottles.feat.bottle.arrivedbottles.ArrivedBottlesRoute
import com.team.bottles.feat.bottle.bottlebox.BottleBoxRoute

fun NavGraphBuilder.arrivedBottlesScreen(
    navigateToSandBeach: () -> Unit
) {
    composable<BottleNavigator.ArrivedBottles> {
        ArrivedBottlesRoute(navigateToSandBeach = navigateToSandBeach)
    }
}

fun NavGraphBuilder.bottleBoxScreen(
    navigateToBottle: (Int) -> Unit
) {
    composable<MainNavigator.BottlesBox> {
        BottleBoxRoute(navigateToBottle = navigateToBottle)
    }
}

fun NavGraphBuilder.bottleScreen(
    navigateToBottleBox: () -> Unit
) {
    composable<BottleNavigator.Bottle> {
        BottleRoute(navigateToBottleBox = navigateToBottleBox)
    }
}
