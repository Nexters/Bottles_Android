package com.team.bottles.navigation

import ArrivedBottlesNavigator
import LoginNavigator
import MainNavigator
import OnboardingNavigator
import PingPongNavigator
import ProfileNavigator
import ReportNavigator
import SettingNavigator
import SplashNavigator
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team.bottles.feat.bottle.navigation.arrivedBottlesScreen
import com.team.bottles.feat.bottle.navigation.bottleBoxScreen
import com.team.bottles.feat.login.navigation.loginScreen
import com.team.bottles.feat.mypage.navigation.myPageScreen
import com.team.bottles.feat.onboarding.navigation.onboardingScreen
import com.team.bottles.feat.pingpong.navigation.pingPongScreen
import com.team.bottles.feat.profile.navigation.createProfileScreen
import com.team.bottles.feat.profile.navigation.introductionScreen
import com.team.bottles.feat.report.navigation.reportScreen
import com.team.bottles.feat.sandbeach.navigation.sandBeachScreen
import com.team.bottles.feat.setting.navigation.accountSettingScreen
import com.team.bottles.feat.setting.navigation.notificationSettingScreen
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
                navigateToLoginEndpoint = ::navigateToLoginEndpoint,
                navigateToOnboarding = ::navigateToOnboarding
            )
            loginScreen(
                navigateToOnboarding = ::navigateToOnboarding,
                navigateToSandBeach = ::navigateToSandBeach,
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
            reportScreen(
                navigateToPingPong = { popBackStack() },
                navigateToBottleBox = ::navigateToBottleBox
            )
            accountSettingScreen(
                navigateToLoginEndpoint = ::navigateToLoginEndpoint,
                navigateToMyPage = ::navigateToMyPage
            )
            notificationSettingScreen(navigateToMyPage = ::navigateToMyPage)
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

fun NavController.navigateToReport(userId: Long, userName: String, userImageUrl: String, userAge: Int) =
    navigate(ReportNavigator(
        userAge = userAge,
        userName = userName,
        userId = userId,
        userImageUrl = userImageUrl))

fun NavController.navigateToSettingAccountManagement() =
    navigate(SettingNavigator.Account)

fun NavController.navigateToSettingNotification() =
    navigate(SettingNavigator.Notification)