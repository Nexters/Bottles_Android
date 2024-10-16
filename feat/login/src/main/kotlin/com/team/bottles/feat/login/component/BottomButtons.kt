package com.team.bottles.feat.login.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun BottomButtons(
    onClickKakaoLogin: () -> Unit,
) {
    val context = LocalContext.current
    val url1 = buildAnnotatedString {
        append("로그인 버튼을 누르면 ")

        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(
                tag = "개인정보처리방침 URL",
                annotation = "https://spiral-ogre-a4d.notion.site/abb2fd284516408e8c2fc267d07c6421"
            )
            append("개인정보처리방침")
        }
    }

    val url2 = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(
                tag = "이용약관 URL",
                annotation = "https://spiral-ogre-a4d.notion.site/240724-e3676639ea864147bb293cfcda40d99f"
            )
            append("보틀이용약관")
        }

        append("에 동의한 것으로 간주합니다.")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KakaoLoginButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = stringResource(id = R.string.kakao_login),
            onClick = onClickKakaoLogin,
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.small))

        Text(
            modifier = Modifier
                .noRippleClickable(
                    onClick = {
                        url1.getStringAnnotations(tag = "개인정보처리방침 URL", start = 0, end = url1.length)
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                context.startActivity(intent)
                            }
                    }
                ),
            text = url1,
            textAlign = TextAlign.Center,
            style = BottlesTheme.typography.caption,
            color = BottlesTheme.color.text.secondary
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraSmall))

        Text(
            modifier = Modifier
                .noRippleClickable(
                    onClick = {
                        url2.getStringAnnotations(tag = "이용약관 URL", start = 0, end = url2.length)
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                context.startActivity(intent)
                            }
                    }
                ),
            text = url2,
            textAlign = TextAlign.Center,
            style = BottlesTheme.typography.caption,
            color = BottlesTheme.color.text.secondary
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomButtonsPreview() {
    BottlesTheme {
        BottomButtons(
            onClickKakaoLogin = {},
        )
    }
}