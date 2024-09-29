package com.team.bottles.core.designsystem.components.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.components.etc.chips.BottlesChip
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesBalloonPopup(
    modifier: Modifier = Modifier,
    text: AnnotatedString
) {
    val shape = RoundedCornerShape(20.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .shadow(
                    elevation = 5.dp,
                    shape = shape
                )
                .height(height = 42.dp)
                .background(
                    color = BottlesTheme.color.container.primary,
                    shape = shape
                )
                .padding(horizontal = BottlesTheme.spacing.large),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.secondary
            )
        }

        Icon(
            modifier = Modifier.offset(y = (-0.2).dp),
            painter = painterResource(id = R.drawable.ic_balloon_vertex_10_6),
            contentDescription = null,
            tint = BottlesTheme.color.container.primary
        )
    }
}

@Composable
fun BottlesBalloonPopup(
    modifier: Modifier = Modifier,
    text: String
) {
    val shape = RoundedCornerShape(20.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .shadow(
                    elevation = 5.dp,
                    shape = shape
                )
                .height(height = 42.dp)
                .background(
                    color = BottlesTheme.color.container.primary,
                    shape = shape
                )
                .padding(horizontal = BottlesTheme.spacing.large),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.secondary
            )
        }

        Icon(
            modifier = Modifier.offset(y = (-0.2).dp),
            painter = painterResource(id = R.drawable.ic_balloon_vertex_10_6),
            contentDescription = null,
            tint = BottlesTheme.color.container.primary
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
    val shape = RoundedCornerShape(20.dp)

    Box {
        Column(
            modifier = modifier
                .shadow(
                    elevation = 8.dp,
                    shape = shape
                )
                .background(
                    color = BottlesTheme.color.container.primary,
                    shape = shape
                )
                .padding(all = BottlesTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(space = BottlesTheme.spacing.small)
        ) {
            var textWidth by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current

            Text(
                text = text,
                style = BottlesTheme.typography.subTitle2,
                color = BottlesTheme.color.text.secondary,
                onTextLayout = { textLayoutResult ->
                    textWidth = with(density) { textLayoutResult.size.width.toDp() }
                }
            )

            BottlesSolidButton(
                modifier = Modifier.width(width = textWidth),
                buttonType = SolidButtonType.SM,
                text = buttonText,
                onClick = onClick,
            )
        }

        Icon(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (5).dp),
            painter = painterResource(id = R.drawable.ic_balloon_vertex_10_6),
            contentDescription = null,
            tint = BottlesTheme.color.container.primary
        )
    }
}

@Composable
fun BottlesBalloonPopupWithChip(
    modifier: Modifier = Modifier,
    text: String,
    count: Int
) {
    Box(modifier = modifier) {
        BottlesBalloonPopup(
            text = text
        )
        BottlesChip(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-12).dp),
            number = count
        )
    }
}

@Preview(heightDp = 400, widthDp = 300, showBackground = true)
@Composable
private fun BottlesBalloonPopupPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            BottlesBalloonPopup(
                text = "보틀을 클릭해 보세요"
            )
            BottlesBalloonPopupWithButton(
                text = "자기소개 작성 후 열어볼 수 있어요",
                buttonText = "자기소개 작성하기",
                onClick = {}
            )
            BottlesBalloonPopup(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF615EFA))) {
                        append("00")
                    }
                    append("시간후 새로운 보틀이 도착해요")
                }
            )
            BottlesBalloonPopupWithChip(
                text = "보틀을 클릭해 보세요",
                count = 3
            )
        }
    }
}