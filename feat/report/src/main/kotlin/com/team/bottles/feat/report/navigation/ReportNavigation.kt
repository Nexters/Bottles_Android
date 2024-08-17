package com.team.bottles.feat.report.navigation

import ReportNavigator
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.team.bottles.feat.report.ReportRoute

fun NavGraphBuilder.reportScreen(
    navigateToPingPong: () -> Unit,
) {
    composable<ReportNavigator> {
        ReportRoute(
            navigateToPingPong = navigateToPingPong,
        )
    }
}