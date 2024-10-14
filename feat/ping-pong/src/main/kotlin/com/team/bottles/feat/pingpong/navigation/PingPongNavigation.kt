package com.team.bottles.feat.pingpong.navigation

import PingPongDetailNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.pingpong.PingPongRoute
import com.team.bottles.feat.pingpong.detail.PingPongDetailRoute

fun NavGraphBuilder.pingPongDetailScreen(
    navigateToPingPong: () -> Unit,
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
        PingPongDetailRoute(
            navigateToPingPong = navigateToPingPong,
            navigateToReport = navigateToReport
        )
    }
}

fun NavGraphBuilder.pingPongScreen(
    innerPadding: PaddingValues,
    navigateToPingPongDetail: (Long) -> Unit,
    navigateToSandBeach: () -> Unit
) {
    composable<MainNavigator.PingPong>(
        enterTransition = {
            when (initialState.destination.route) {
                "PingPongDetailNavigator" -> slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
                else -> null
            }
        }
    ) {
        PingPongRoute(
            innerPadding = innerPadding,
            navigateToPingPongDetail = navigateToPingPongDetail,
            navigateToSandBeach = navigateToSandBeach
        )
    }
}
