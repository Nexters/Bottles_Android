package com.team.bottles.feat.login.navigation

import LoginNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.login.LoginRoute
import com.team.bottles.feat.login.smslogin.SmsLoginRoute

fun NavGraphBuilder.loginScreen(
    navigateToOnboarding: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateToSmsLogin: () -> Unit,
    navigateToCreateProfile: () -> Unit
) {
    composable<LoginNavigator.Endpoint> {
        LoginRoute(
            navigateToOnboarding = navigateToOnboarding,
            navigateToSandBeach = navigateToSandBeach,
            navigateToSignup = navigateToSignup,
            navigateToSmsLogin = navigateToSmsLogin,
            navigateToCreateProfile = navigateToCreateProfile
        )
    }
}

fun NavGraphBuilder.smsLoginScreen(
    navigateToLogin: () -> Unit,
    navigateToSandBeach: () -> Unit,
) {
    composable<LoginNavigator.SmsLogin> {
        SmsLoginRoute(
            navigateToLogin = navigateToLogin,
            navigateToSandBeach = navigateToSandBeach,
        )
    }
}
