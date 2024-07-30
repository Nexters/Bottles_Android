package com.team.bottles.core.designsystem.components.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesBalloonPopup(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .background(
                    color = BottlesTheme.color.container.primary,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = BottlesTheme.color.border.primary,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 7.5f.dp
                )
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
        }
        Icon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_balloon_vertex_10_6),
            contentDescription = null,
            tint = BottlesTheme.color.border.primary
        )
    }
}

@Composable
fun BottlesBalloonPopupWithButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(
                    color = BottlesTheme.color.container.primary,
                    shape = BottlesTheme.shape.radius12
                )
                .border(
                    width = 1.dp,
                    color = BottlesTheme.color.border.primary,
                    shape = BottlesTheme.shape.radius12
                )
                .padding(paddingValues = BottlesTheme.padding.padding16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.spacing4))
            BottlesSolidButton(
                buttonType = SolidButtonType.XS,
                text = buttonText,
                onClick = onClick,
                contentHorizontalPadding = 12.dp
            )
        }
        Icon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_balloon_vertex_10_6),
            contentDescription = null,
            tint = BottlesTheme.color.border.primary
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BottlesBalloonPopupPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesBalloonPopup(
                text = "새로운 보틀이 도착했어요!"
            )
            BottlesBalloonPopupWithButton(
                text = "자기소개 작성 후 열어볼 수 있어요",
                buttonText = "자기소개 작성하기",
                onClick = {}
            )
        }
    }
}