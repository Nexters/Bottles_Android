package com.team.bottles.feat.report.navigation

import ReportNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.report.ReportRoute

fun NavGraphBuilder.reportScreen(
    navigateToPingPong: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    composable<ReportNavigator>(
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
        ReportRoute(
            navigateToPingPong = navigateToPingPong,
            navigateToBottleBox = navigateToBottleBox
        )
    }
}