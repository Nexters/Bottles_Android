package com.team.bottles.feat.signup.navigation

import SignupNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.signup.SignupRoute

fun NavGraphBuilder.signupScreen(
    navigateToLoginEndpoint: () -> Unit,
    navigateToSandBeach: () -> Unit
) {
    composable<SignupNavigator> {
        SignupRoute(
            navigateToLoginEndpoint = navigateToLoginEndpoint,
            navigateToSandBeach = navigateToSandBeach
        )
    }
}