package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun EmptyBottles(
    modifier: Modifier = Modifier,
    onClickGoToSandBeach: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraLarge,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            modifier = Modifier.size(size = 180.dp),
            painter = painterResource(id = R.drawable.illustration_basket),
            contentDescription = null
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraSmall,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = "아직 대화를 시작하지 않으셨군요!",
                style = BottlesTheme.typography.subTitle1,
                color = BottlesTheme.color.text.primary
            )
            Text(
                text = "마음에 드는 상대를 찾아\n" +
                        "가치관 문답을 시작해 볼까요?",
                textAlign = TextAlign.Center,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.tertiary
            )
        }
        BottlesSolidButton(
            buttonType = SolidButtonType.SM,
            text = "모래사장 바로가기",
            onClick = onClickGoToSandBeach,
            contentHorizontalPadding = BottlesTheme.spacing.small
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyBottlesPreview() {
    BottlesTheme {
        Column{
            EmptyBottles(
                onClickGoToSandBeach = {}
            )
        }
    }
}