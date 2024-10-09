package com.team.bottles.core.designsystem.components.bars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.etc.menu.BottlesBottomNavItem
import com.team.bottles.core.designsystem.theme.BottlesTheme

const val SAND_BEACH_ROUTE = "MainNavigator.SandBeach"
const val BOTTLES_BOX_ROUTE = "MainNavigator.BottlesBox"
const val MY_PAGE_ROUTE = "MainNavigator.MyPage"
const val LIKE_ROUTE = "MainNavigator.Like"

enum class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    SAND_BEACH(
        route = SAND_BEACH_ROUTE,
        icon = R.drawable.ic_beach_32,
        label = R.string.sand_beach
    ),
    LIKE(
        route = LIKE_ROUTE,
        icon = R.drawable.ic_heart_32,
        label = R.string.heart
    ),
    BOTTLE_BOX(
        route = BOTTLES_BOX_ROUTE,
        icon = R.drawable.ic_bottle_32,
        label = R.string.bottle_box
    ),
    MY_PAGE(
        route = MY_PAGE_ROUTE,
        icon = R.drawable.ic_user_32,
        label = R.string.my_page
    ),
    ;
}

@Composable
fun BottlesBottomNavBar(
    modifier: Modifier = Modifier,
    onClickItem: (BottomNavItem) -> Unit,
    currentSelectedItem: BottomNavItem,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
            )
            .background(
                color = BottlesTheme.color.background.secondary,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(
                vertical = BottlesTheme.spacing.extraSmall,
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        BottomNavItem.entries.forEach { navItem ->
            BottlesBottomNavItem(
                onClick = { onClickItem(navItem) },
                icon = navItem.icon,
                label = navItem.label,
                isSelected = navItem == currentSelectedItem
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun BottlesBottomNavBarPreview() {
    BottlesTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottlesBottomNavBar(
                    onClickItem = { },
                    currentSelectedItem = BottomNavItem.BOTTLE_BOX
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding))
        }
    }
}