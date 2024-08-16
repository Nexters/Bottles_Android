package com.team.bottles.feat.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun BottomButtons(
    onClickKakaoLogin: () -> Unit,
    onClickNormalLogin: () -> Unit,
    onClickSignup: () -> Unit
) {
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

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.medium
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = BottlesTheme.spacing.extraSmall
                    )
                    .noRippleClickable(
                        onClick = onClickNormalLogin
                    ),
                text = stringResource(id = R.string.normal_login),
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.enabledSecondary
            )
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = BottlesTheme.spacing.medium
                    )
                    .noRippleClickable(
                        onClick = onClickSignup
                    ),
                text = stringResource(id = R.string.signup),
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.enabledSecondary
            )
        }

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))
    }
}

@Preview
@Composable
private fun BottomButtonsPreview() {
    BottlesTheme {
        BottomButtons(
            onClickKakaoLogin = {},
            onClickNormalLogin = {},
            onClickSignup = {}
        )
    }
}