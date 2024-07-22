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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team.bottles.core.designsystem.R

private val wantedSansStd =
    FontFamily(
        Font(
            R.font.wanted_sans_std_bold,
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(
            R.font.wanted_sans_std_medium,
            FontWeight.Medium,
            FontStyle.Normal
        ),
        Font(
            R.font.wanted_sans_std_semi_bold,
            FontWeight.SemiBold,
            FontStyle.Normal
        )
    )

private val roboto =
    FontFamily(
        Font(
            R.font.roboto_medium,
            FontWeight.Medium,
            FontStyle.Normal
        )
    )

private val laundryGothic =
    FontFamily(
        Font(
            R.font.laundry_gothic_bold,
            FontWeight.Bold,
            FontStyle.Normal
        )
    )

@Immutable
data class BottlesTypography(
    val title1: TextStyle,
    val title2: TextStyle,
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
                fontFamily = laundryGothic,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.sp,
                lineHeight = 24.sp * 1.3f,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            title2 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.sp,
                lineHeight = 20.sp * 1.3f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            subTitle1 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                letterSpacing = 0.sp,
                lineHeight = 16.sp * 1.3f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            subTitle2 =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                letterSpacing = 0.sp,
                lineHeight = 14.sp * 1.3f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            body =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.sp,
                lineHeight = 14.sp * 1.5f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            caption =
            TextStyle(
                fontFamily = wantedSansStd,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                lineHeight = 12.sp * 1.5f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            kakaoLogin =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.15.sp,
                lineHeight = 14.sp * 1.4f,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
        ) {
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