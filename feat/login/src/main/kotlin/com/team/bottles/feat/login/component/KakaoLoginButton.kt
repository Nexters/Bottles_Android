package com.team.bottles.feat.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.debounceClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun KakaoLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String
) {
    Row(
        modifier = modifier
            .height(height = 64.dp)
            .background(
                color = Color(0xFFFEE500),
                shape = BottlesTheme.shape.small
            )
            .clip(shape = BottlesTheme.shape.small)
            .debounceClickable(
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 17.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_kakao_logo_24),
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            modifier = Modifier.width(206.dp),
            textAlign = TextAlign.Center,
            text = buttonText,
            style = BottlesTheme.typography.subTitle1,
            color = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_kakao_logo_24),
            contentDescription = null,
            tint = Color.Transparent
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KakaoLoginButtonPreview() {
    BottlesTheme {
        KakaoLoginButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            buttonText = "카카오 로그인"
        )
    }
}