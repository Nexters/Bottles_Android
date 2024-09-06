package com.team.bottles.feat.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageUiState

@Composable
internal fun MyPageScreen(
    uiState: MyPageUiState,
    onIntent: (MyPageIntent) -> Unit
) {
    
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    BottlesTheme {
        MyPageScreen(
            uiState = MyPageUiState(),
            onIntent = {}
        )
    }
}