package com.team.bottles.feat.pingpong.navigation

import PingPongNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.pingpong.PingPongRoute

fun NavGraphBuilder.pingPongScreen(
    navigateToBottleBox: () -> Unit,
    navigateToReport: (userId: Long, userName: String, userImageUrl: String, userAge: Int) -> Unit
) {
    composable<PingPongNavigator> {
        PingPongRoute(
            navigateToBottleBox = navigateToBottleBox,
            navigateToReport = navigateToReport
        )
    }
}
