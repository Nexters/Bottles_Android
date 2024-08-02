package com.team.bottles.navigation

import BottleNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import LoginNavigator
import MainNavigator
import OnboardingNavigator
import ProfileNavigator
import SignupNavigator
import com.team.bottles.feat.bottle.navigation.arrivedBottlesScreen
import com.team.bottles.feat.bottle.navigation.bottleBoxScreen
import com.team.bottles.feat.bottle.navigation.bottleScreen
import com.team.bottles.feat.login.navigation.loginScreen
import com.team.bottles.feat.login.navigation.smsLoginScreen
import com.team.bottles.feat.mypage.navigation.myPageScreen
import com.team.bottles.feat.onboarding.navigation.onboardingScreen
import com.team.bottles.feat.profile.navigation.createProfileScreen
import com.team.bottles.feat.profile.navigation.introductionScreen
import com.team.bottles.feat.sandbeach.navigation.sandBeachScreen
import com.team.bottles.feat.signup.navigation.signupScreen

@Composable
fun BottlesNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = LoginNavigator.Endpoint
    ) {
        with(navHostController) {
            loginScreen(
                navigateToOnboarding = ::navigateToOnboarding,
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToSignup = ::navigateToSignup,
                navigateToSmsLogin = ::navigateToSmsLogin
            )
            onboardingScreen(navigateToCreateProfile = ::navigateToCreateProfile)
            createProfileScreen(navigateToSandBeach = ::navigateToSandBeach)
            sandBeachScreen(
                navigateToIntroduction = ::navigateToIntroduction,
                navigateToArrivedBottles = ::navigateToArrivedBottles
            )
            arrivedBottlesScreen(navigateToSandBeach = ::navigateToSandBeach)
            bottleBoxScreen(navigateToBottle = ::navigateToBottle)
            introductionScreen(navigateToSandBeach = ::navigateToSandBeach)
            bottleScreen(navigateToBottleBox = ::navigateToBottleBox)
            myPageScreen(navigateToLogin = ::navigateToLogin)
            signupScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToLogin = ::navigateToLogin
            )
            smsLoginScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToLogin = ::navigateToLogin
            )
        }
    }
}

fun NavController.navigateToOnboarding() =
    navigate(OnboardingNavigator) {
        popUpTo(graph.id)
    }

fun NavController.navigateToCreateProfile() =
    navigate(ProfileNavigator.CreateProfile)

fun NavController.navigateToSandBeach() =
    navigate(MainNavigator.SandBeach) {
        popUpTo(graph.id)
    }

fun NavController.navigateToIntroduction() =
    navigate(ProfileNavigator.Introduction)

fun NavController.navigateToArrivedBottles() =
    navigate(BottleNavigator.ArrivedBottles)

fun NavController.navigateToBottle() =
    navigate(BottleNavigator.Bottle)

fun NavController.navigateToBottleBox() =
    navigate(MainNavigator.BottlesBox) {
        popUpTo(graph.id)
    }

fun NavController.navigateToLogin() =
    navigate(LoginNavigator.Endpoint) {
        popUpTo(graph.id)
    }

fun NavController.navigateToMyPage() =
    navigate(MainNavigator.MyPage) {
        popUpTo(graph.id)
    }

fun NavController.navigateToSignup() =
    navigate(SignupNavigator)

fun NavController.navigateToSmsLogin() =
    navigate(LoginNavigator.SmsLogin)