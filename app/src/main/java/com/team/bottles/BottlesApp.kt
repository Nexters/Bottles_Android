package com.team.bottles

import MainNavigator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.team.bottles.core.designsystem.components.bars.BottlesBottomNavBar
import com.team.bottles.core.designsystem.components.bars.BottomNavItem
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.navigation.BottlesNavHost
import com.team.bottles.navigation.navigateToTopLevelDestination

@Composable
fun BottlesApp() {
    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottlesScaffold(
        onClickNavItem = { bottomNavItem ->
            when (bottomNavItem) {
                BottomNavItem.BOTTLE_BOX -> navHostController.navigateToTopLevelDestination(MainNavigator.BottlesBox)
                BottomNavItem.LIKE -> navHostController.navigateToTopLevelDestination(MainNavigator.Like)
                BottomNavItem.SAND_BEACH -> navHostController.navigateToTopLevelDestination(MainNavigator.SandBeach)
                BottomNavItem.MY_PAGE -> navHostController.navigateToTopLevelDestination(MainNavigator.MyPage)
            }
        },
        currentRoute = currentRoute
    ) { innerPadding ->
        BottlesNavHost(
            navHostController = navHostController,
            innerPadding = innerPadding
        )
    }
}

@Composable
internal fun BottlesScaffold(
    onClickNavItem: (BottomNavItem) -> Unit,
    currentRoute: String?,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
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
                .background(color = BottlesTheme.color.background.primary)
        ) {
            content.invoke(innerPadding)
        }
    }
}