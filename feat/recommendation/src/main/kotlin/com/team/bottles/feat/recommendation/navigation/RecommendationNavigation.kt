package com.team.bottles.feat.recommendation.navigation

import RecommendationDetailNavigator
import RecommendationNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.team.bottles.feat.recommendation.RecommendationRoute
import com.team.bottles.feat.recommendation.detail.RecommendationDetailRoute
import org.json.JSONObject

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
        }
    ) {
        RecommendationRoute(
            navigateToSandBeach = navigateToSandBeach,
            navigateToRecommendationDetail = navigateToRecommendationDetail
        )
    }
}

fun NavGraphBuilder.recommendationDetailScreen(
    navigateToRecommendation: () -> Unit,
) {
    composable<RecommendationDetailNavigator>(
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) { backStackEntry ->
        val hrefJson = backStackEntry.toRoute<RecommendationDetailNavigator>().href
        val href = JSONObject(hrefJson).getString("url")

        RecommendationDetailRoute(
            href = href,
            navigateToRecommendation = navigateToRecommendation,
        )
    }
}