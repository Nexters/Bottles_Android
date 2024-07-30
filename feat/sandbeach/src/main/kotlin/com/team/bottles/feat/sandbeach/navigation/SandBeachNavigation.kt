package com.team.bottles.feat.sandbeach.navigation

import MainNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.sandbeach.SandBeachRoute

fun NavGraphBuilder.sandBeachScreen(
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
) {
    composable<MainNavigator.SandBeach> {
        SandBeachRoute(
            navigateToIntroduction = navigateToIntroduction,
            navigateToArrivedBottles = navigateToArrivedBottles
        )
    }
}
