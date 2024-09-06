package com.team.bottles.core.designsystem.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team.bottles.core.designsystem.foundation.laundryGothic
import com.team.bottles.core.designsystem.foundation.roboto
import com.team.bottles.core.designsystem.foundation.wantedSansStd

@Immutable
data class BottlesTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val subTitle1: TextStyle,
    val subTitle2: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val kakaoLogin: TextStyle,
) {
    companion object {
        fun defaultTypography(): BottlesTypography = BottlesTypography(
            title1 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = 0.sp,
                lineHeight = 41.6f.sp,
            ),
            title2 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.sp,
                lineHeight = 31.2f.sp,
            ),
            title3 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.sp,
                lineHeight = 26.sp,
            ),
            subTitle1 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                letterSpacing = 0.sp,
                lineHeight = 20.8f.sp,
            ),
            subTitle2 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                letterSpacing = 0.sp,
                lineHeight = 18.2f.sp,
            ),
            body =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.sp,
                lineHeight = 21.sp,
            ),
            caption =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                lineHeight = 18.sp,
            ),
            kakaoLogin =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.15.sp,
                lineHeight = 19.6f.sp,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TypographyPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "아직 보틀을\n찾지 못했어요",
                style = BottlesTheme.typography.title1,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "Title1",
                style = BottlesTheme.typography.title1,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "Title2",
                style = BottlesTheme.typography.title2,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "SubTitle1",
                style = BottlesTheme.typography.subTitle1,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "SubTitle2",
                style = BottlesTheme.typography.subTitle2,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "Body",
                style = BottlesTheme.typography.body,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "Caption",
                style = BottlesTheme.typography.caption,
                color = Color.Black
            )
            Text(
                modifier = Modifier.border(1.dp, Color.Blue),
                text = "Kakao login",
                style = BottlesTheme.typography.kakaoLogin,
                color = Color.Black
            )
        }
    }
}