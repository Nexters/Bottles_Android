package com.team.bottles.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesErrorScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
    onClickRetryButton: () -> Unit,
    isVisibleLeadingIcon: Boolean = false,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BottlesTheme.color.background.primary)
    ) {
        BottlesTopBar(
            modifier = Modifier.align(Alignment.TopCenter),
            leadingIcon = {
                if (isVisibleLeadingIcon) {
                    Icon(
                        modifier = Modifier.noRippleClickable(onClick = onClickBackButton),
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = BottlesTheme.color.icon.primary
                    )
                }
            }
        )

        Column(
            modifier = Modifier.align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(180.dp),
                painter = painterResource(id = R.drawable.illustration_loudspeaker),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

            Text(
                text = "앗! 오류가 발생했어요",
                style = BottlesTheme.typography.subTitle1,
                color = BottlesTheme.color.text.primary
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraSmall))

            Text(
                text = "일시적인 오류로\n화면을 불러오지 못했어요",
                textAlign = TextAlign.Center,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.tertiary
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

            BottlesSolidButton(
                buttonType = SolidButtonType.SM,
                text = "다시 시도하기",
                onClick = onClickRetryButton,
                contentHorizontalPadding = BottlesTheme.spacing.small
            )
        }
    }
}

@Preview
@Composable
private fun BottlesErrorScreenPreview() {
    BottlesTheme {
        BottlesErrorScreen(
            onClickBackButton = { },
            onClickRetryButton = {}
        )
    }
}