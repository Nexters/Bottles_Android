package com.team.bottles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.team.bottles.core.designsystem.components.bars.BottlesBottomNavBar
import com.team.bottles.core.designsystem.components.bars.BottomNavItem
import com.team.bottles.navigation.BottlesNavHost
import com.team.bottles.navigation.navigateToBottleBox
import com.team.bottles.navigation.navigateToMyPage
import com.team.bottles.navigation.navigateToSandBeach

@Composable
fun BottlesApp() {
    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottlesScaffold(
        onClickNavItem = { bottomNavItem ->
            when (bottomNavItem) {
                BottomNavItem.BOTTLE_BOX -> navHostController.navigateToBottleBox()
                BottomNavItem.SAND_BEACH -> navHostController.navigateToSandBeach()
                BottomNavItem.MY_PAGE -> navHostController.navigateToMyPage()
            }
        },
        currentRoute = currentRoute
    ) {
        BottlesNavHost(navHostController = navHostController)
    }
}

@Composable
internal fun BottlesScaffold(
    onClickNavItem: (BottomNavItem) -> Unit,
    currentRoute: String?,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (BottomNavItem.entries.any { it.route == currentRoute }) {
                BottlesBottomNavBar(
                    onClickItem = onClickNavItem,
                    currentSelectedItem = BottomNavItem.entries.find { it.route == currentRoute }
                        ?: BottomNavItem.SAND_BEACH
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content.invoke()
        }
    }
}