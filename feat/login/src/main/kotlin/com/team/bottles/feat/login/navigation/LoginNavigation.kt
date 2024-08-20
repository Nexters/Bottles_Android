package com.team.bottles.feat.login.navigation

import LoginNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.login.LoginRoute
import com.team.bottles.feat.login.smslogin.SmsLoginRoute

fun NavGraphBuilder.loginScreen(
    navigateToOnboarding: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToSmsLogin: () -> Unit,
    navigateToCreateProfile: () -> Unit
) {
    composable<LoginNavigator.Endpoint> {
        LoginRoute(
            navigateToOnboarding = navigateToOnboarding,
            navigateToSandBeach = navigateToSandBeach,
            navigateToSmsLogin = navigateToSmsLogin,
            navigateToCreateProfile = navigateToCreateProfile
        )
    }
}

fun NavGraphBuilder.smsLoginScreen(
    navigateToLoginEndPoint: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToOnboarding: () -> Unit,
) {
    composable<LoginNavigator.SmsLogin> {
        SmsLoginRoute(
            navigateToLoginEndPoint = navigateToLoginEndPoint,
            navigateToSandBeach = navigateToSandBeach,
            navigateToOnboarding = navigateToOnboarding
        )
    }
}
