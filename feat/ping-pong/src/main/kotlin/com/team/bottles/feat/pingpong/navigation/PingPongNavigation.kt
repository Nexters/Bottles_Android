package com.team.bottles.feat.pingpong.navigation

import PingPongNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.pingpong.PingPongRoute

fun NavGraphBuilder.pingPongScreen(
    navigateToBottleBox: () -> Unit
) {
    composable<PingPongNavigator> {
        PingPongRoute(navigateToBottleBox = navigateToBottleBox)
    }
}
