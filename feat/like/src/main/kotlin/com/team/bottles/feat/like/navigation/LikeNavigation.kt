package com.team.bottles.feat.like.navigation

import MainNavigator
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.like.LikeRoute

fun NavGraphBuilder.likeScreen(
    innerPadding: PaddingValues,
    navigateToLikeDetail: () -> Unit
) {
    composable<MainNavigator.Like>(
        enterTransition = {
            when (initialState.destination.route) {
                "PingPongNavigator" -> slideIntoContainer( // TODO : 호감 상세 화면으로 넘어갈 시 네비게이터명 변경
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

