package com.team.bottles.navigation

import LikeDetailNavigator
import LoginNavigator
import MainNavigator
import OnboardingNavigator
import PingPongDetailNavigator
import ProfileNavigator
import RecommendationDetailNavigator
import RecommendationNavigator
import ReportNavigator
import SettingNavigator
import SplashNavigator
import android.webkit.WebView
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import com.team.bottles.core.designsystem.theme.LocalLikeTabWebViewComposition
import com.team.bottles.feat.like.navigation.likeDetailScreen
import com.team.bottles.feat.like.navigation.likeScreen
import com.team.bottles.feat.login.navigation.loginScreen
import com.team.bottles.feat.mypage.navigation.myPageScreen
import com.team.bottles.feat.onboarding.navigation.onboardingScreen
import com.team.bottles.feat.pingpong.navigation.pingPongDetailScreen
import com.team.bottles.feat.pingpong.navigation.pingPongScreen
import com.team.bottles.feat.profile.navigation.createProfileScreen
import com.team.bottles.feat.profile.navigation.editProfileScreen
import com.team.bottles.feat.profile.navigation.introductionScreen
import com.team.bottles.feat.recommendation.navigation.recommendationDetailScreen
import com.team.bottles.feat.recommendation.navigation.recommendationScreen
import com.team.bottles.feat.report.navigation.reportScreen
import com.team.bottles.feat.sandbeach.navigation.sandBeachScreen
import com.team.bottles.feat.setting.navigation.accountSettingScreen
import com.team.bottles.feat.setting.navigation.notificationSettingScreen
import com.team.bottles.feat.splash.splashScreen

@Composable
fun BottlesNavHost(
    navHostController: NavHostController,
    innerPadding: PaddingValues,
) {
    val context = LocalContext.current
    val likeTabWebView = remember { WebView(context) }

    CompositionLocalProvider(
        LocalLikeTabWebViewComposition provides likeTabWebView
    ) {
        NavHost(
            navController = navHostController,
            startDestination = SplashNavigator,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
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
                    innerPadding = innerPadding,
                    navigateToIntroduction = ::navigateToIntroduction,
                    navigateToArrivedBottles = ::navigateToArrivedBottles,
                    navigateToPingPong = { navigateToTopLevelDestination(route = MainNavigator.PingPong) }
                )
                recommendationScreen(
                    navigateToSandBeach = { popBackStack() },
                    navigateToRecommendationDetail = ::navigateToRecommendationDetail
                )
                recommendationDetailScreen(navigateToRecommendation = { popBackStack() })
                pingPongScreen(
                    innerPadding = innerPadding,
                    navigateToPingPongDetail = ::navigateToPingPongDetail,
                    navigateToSandBeach = { navigateToTopLevelDestination(MainNavigator.SandBeach) }
                )
                pingPongDetailScreen(
                    navigateToPingPong = { popBackStack() },
                    navigateToReport = ::navigateToReport
                )
                introductionScreen(navigateToSandBeach = { popBackStack() })
                myPageScreen(
                    innerPadding = innerPadding,
                    navigateToEditProfile = ::navigateToEditProfile,
                    navigateToSettingNotification = ::navigateToSettingNotification,
                    navigateToSettingAccountManagement = ::navigateToSettingAccountManagement,
                )
                reportScreen(
                    navigateToPingPongDetail = { popBackStack() },
                    navigateToPingPong = {
                        navigateToPingPong {
                            popUpTo(graph.id) { inclusive = true }
                            restoreState = true
                        }
                    }
                )
                accountSettingScreen(
                    navigateToLoginEndpoint = ::navigateToLoginEndpoint,
                    navigateToMyPage = { popBackStack() }
                )
                notificationSettingScreen(navigateToMyPage = { popBackStack() })
                editProfileScreen(navigateToMyPage = { popBackStack() })
                likeScreen(
                    innerPadding = innerPadding,
                    navigateToLikeDetail = ::navigateToLikeDetail
                )
                likeDetailScreen(
                    navigateToQna = {
                        navigateToPingPong {
                            popUpTo(graph.id) { inclusive = true }
                        }
                    },
                    navigateToLikeDetail = { popBackStack() }
                )
            }
        }
    }
}

fun NavController.navigateToTopLevelDestination(route: MainNavigator) =
    navigate(route) {
        popUpTo(graph.id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
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
    navigate(RecommendationNavigator)

fun NavController.navigateToPingPongDetail(bottleId: Long) =
    navigate(PingPongDetailNavigator(bottleId = bottleId))

fun NavController.navigateToPingPong(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(
        route = MainNavigator.PingPong,
    ) {
        navOptions()
    }
}

fun NavController.navigateToLoginEndpoint() =
    navigate(LoginNavigator.Endpoint) {
        popUpTo(graph.id) { inclusive = true }
        clearBackStack<MainNavigator.Like>()
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

fun NavController.navigateToEditProfile() =
    navigate(ProfileNavigator.Edit)

fun NavController.navigateToLikeDetail(href: String) =
    navigate(LikeDetailNavigator(href = href))

fun NavController.navigateToRecommendationDetail(href: String) =
    navigate(RecommendationDetailNavigator(href = href))