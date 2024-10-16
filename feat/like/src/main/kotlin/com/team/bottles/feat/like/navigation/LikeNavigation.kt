package com.team.bottles.feat.like.navigation

import LikeDetailNavigator
import MainNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.team.bottles.feat.like.LikeRoute
import com.team.bottles.feat.like.detail.LikeDetailRoute
import org.json.JSONObject

fun NavGraphBuilder.likeScreen(
    innerPadding: PaddingValues,
    navigateToLikeDetail: (href: String) -> Unit
) {
    composable<MainNavigator.Like>(
        enterTransition = {
            when (initialState.destination.route) {
                "LikeDetailNavigator" -> slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
                else -> null
            }
        }
    ) {
        LikeRoute(
            innerPadding = innerPadding,
            navigateToLikeDetail = navigateToLikeDetail,
        )
    }
}

fun NavGraphBuilder.likeDetailScreen(
    navigateToLikeDetail: () -> Unit,
    navigateToQna: () -> Unit,
) {
    composable<LikeDetailNavigator>(
        enterTransition = {
            when (initialState.destination.route) {
                "MainNavigator.Like" -> slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
                else -> null
            }
        }
    ) { backStackEntry ->
        val hrefJson = backStackEntry.toRoute<LikeDetailNavigator>().href
        val href = JSONObject(hrefJson).getString("url")

        LikeDetailRoute(
            href = href,
            navigateToLikeDetail = navigateToLikeDetail,
            navigateToQna = navigateToQna
        )
    }
}