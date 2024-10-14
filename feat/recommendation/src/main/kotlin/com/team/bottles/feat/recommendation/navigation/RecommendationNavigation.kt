package com.team.bottles.feat.recommendation.navigation

import RecommendationNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.recommendation.RecommendationRoute

fun NavGraphBuilder.recommendationScreen(
    navigateToSandBeach: () -> Unit,
    navigateToRecommendationDetail: (href: String) -> Unit,
) {
    composable<RecommendationNavigator>(
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
        RecommendationRoute(
            navigateToSandBeach = navigateToSandBeach,
            navigateToRecommendationDetail = navigateToRecommendationDetail
        )
    }
}
