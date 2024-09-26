package com.team.bottles.feat.sandbeach.navigation

import MainNavigator
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.sandbeach.SandBeachRoute

fun NavGraphBuilder.sandBeachScreen(
    innerPadding: PaddingValues,
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    composable<MainNavigator.SandBeach> {
        SandBeachRoute(
            innerPadding = innerPadding,
            navigateToIntroduction = navigateToIntroduction,
            navigateToArrivedBottles = navigateToArrivedBottles,
            navigateToBottleBox = navigateToBottleBox
        )
    }
}
