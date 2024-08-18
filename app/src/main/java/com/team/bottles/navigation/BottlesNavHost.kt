package com.team.bottles.navigation

import ArrivedBottlesNavigator
import LoginNavigator
import MainNavigator
import OnboardingNavigator
import PingPongNavigator
import ProfileNavigator
import ReportNavigator
import SignupNavigator
import SplashNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team.bottles.feat.bottle.navigation.arrivedBottlesScreen
import com.team.bottles.feat.bottle.navigation.bottleBoxScreen
import com.team.bottles.feat.login.navigation.loginScreen
import com.team.bottles.feat.login.navigation.smsLoginScreen
import com.team.bottles.feat.mypage.navigation.myPageScreen
import com.team.bottles.feat.onboarding.navigation.onboardingScreen
import com.team.bottles.feat.pingpong.navigation.pingPongScreen
import com.team.bottles.feat.profile.navigation.createProfileScreen
import com.team.bottles.feat.profile.navigation.introductionScreen
import com.team.bottles.feat.report.navigation.reportScreen
import com.team.bottles.feat.sandbeach.navigation.sandBeachScreen
import com.team.bottles.feat.signup.navigation.signupScreen
import com.team.bottles.feat.splash.splashScreen

@Composable
fun BottlesNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = SplashNavigator
    ) {
        with(navHostController) {
            splashScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToLoginEndpoint = ::navigateToLoginEndpoint
            )
            loginScreen(
                navigateToOnboarding = ::navigateToOnboarding,
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToSignup = ::navigateToSignup,
                navigateToSmsLogin = ::navigateToSmsLogin,
                navigateToCreateProfile = ::navigateToCreateProfile
            )
            onboardingScreen(
                navigateToCreateProfile = ::navigateToCreateProfile,
                navigateToLoginEndpoint = ::navigateToLoginEndpoint
            )
            createProfileScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToOnboarding = ::navigateToOnboarding
            )
            sandBeachScreen(
                navigateToIntroduction = ::navigateToIntroduction,
                navigateToArrivedBottles = ::navigateToArrivedBottles,
                navigateToBottleBox = ::navigateToBottleBox
            )
            arrivedBottlesScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToBottleBox = ::navigateToBottleBox
            )
            bottleBoxScreen(navigateToPingPong = ::navigateToPingPong)
            introductionScreen(navigateToSandBeach = ::navigateToSandBeach)
            pingPongScreen(
                navigateToBottleBox = ::navigateToBottleBox,
                navigateToReport = ::navigateToReport
            )
            myPageScreen(navigateToLoginEndPoint = ::navigateToLoginEndpoint)
            signupScreen(
                navigateToOnboarding = ::navigateToOnboarding,
                navigateToLoginEndpoint = ::navigateToLoginEndpoint
            )
            smsLoginScreen(
                navigateToSandBeach = ::navigateToSandBeach,
                navigateToLoginEndPoint = ::navigateToLoginEndpoint,
                navigateToOnboarding = ::navigateToOnboarding
            )
            reportScreen(
                navigateToPingPong = { popBackStack() },
                navigateToBottleBox = ::navigateToBottleBox
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
    navigate(ArrivedBottlesNavigator)

fun NavController.navigateToPingPong(bottleId: Long) =
    navigate(PingPongNavigator(bottleId = bottleId))

fun NavController.navigateToBottleBox() =
    navigate(MainNavigator.BottlesBox) {
        popUpTo(graph.id)
    }

fun NavController.navigateToLoginEndpoint() =
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

fun NavController.navigateToReport(userId: Long, userName: String, userImageUrl: String, userAge: Int) =
    navigate(ReportNavigator(
        userAge = userAge,
        userName = userName,
        userId = userId,
        userImageUrl = userImageUrl))