package com.team.bottles.feat.pingpong.navigation

import PingPongDetailNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.pingpong.PingPongRoute

fun NavGraphBuilder.pingPongDetailScreen(
    navigateToBottleBox: () -> Unit,
    navigateToReport: (userId: Long, userName: String, userImageUrl: String, userAge: Int) -> Unit
) {
    composable<PingPongDetailNavigator>(
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        enterTransition = {
            val routeName = initialState.destination.route?.substringBefore("/")

            when (routeName) {
                "ReportNavigator" -> null
                else -> {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                }
            }
        }
    ) {
        PingPongRoute(
            navigateToBottleBox = navigateToBottleBox,
            navigateToReport = navigateToReport
        )
    }
}
