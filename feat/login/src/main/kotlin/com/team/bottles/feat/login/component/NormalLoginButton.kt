package com.team.bottles.feat.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun NormalLoginButton(
    modifier: Modifier = Modifier,
    onClickNormalLogin: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 64.dp)
            .clip(shape = BottlesTheme.shape.medium)
            .background(
                color = Color.White,
                shape = BottlesTheme.shape.medium
            )
            .clickable(
                onClick = onClickNormalLogin
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "일반 로그인",
            style = BottlesTheme.typography.subTitle1,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun NormalLoginButtonPreview() {
    BottlesTheme {
        NormalLoginButton(
            onClickNormalLogin = {}
        )
    }
}