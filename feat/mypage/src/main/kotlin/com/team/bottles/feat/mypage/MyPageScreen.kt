package com.team.bottles.feat.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageUiState

@Composable
internal fun MyPageScreen(
    uiState: MyPageUiState,
    onIntent: (MyPageIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "마이 페이지 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "로그 아웃",
            onClick = { onIntent(MyPageIntent.ClickLogOutButton) },
            contentHorizontalPadding = 12.dp
        )
    }
}