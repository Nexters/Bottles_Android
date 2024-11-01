package com.team.bottles.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.cloudy.cloudy
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.etc.chips.BottlesEtcText
import com.team.bottles.core.designsystem.components.etc.chips.EtcText
import com.team.bottles.core.designsystem.foundation.wantedSansStd
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.model.Bottle

@Composable
fun BottleItem(
    modifier: Modifier = Modifier,
    bottle: Bottle,
    graphicsLayer: GraphicsLayer,
    onClickItem: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(BottlesTheme.padding.medium)
            .debounceNoRippleClickable(onClick = onClickItem),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        BottlesEtcText(
            leftText = bottle.lastStatus,
            rightText = bottle.lastActivatedAt
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = BottlesTheme.spacing.extraSmall
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = bottle.name,
                        style = BottlesTheme.typography.title3,
                        color = BottlesTheme.color.text.secondary
                    )

                    if (!bottle.isRead) {
                        val circleColor = BottlesTheme.color.icon.update

                        Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.doubleExtraSmall))
                        Canvas(modifier = Modifier.size(4.dp)) {
                            drawCircle(color = circleColor)
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${bottle.age}세",
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.secondary
                    )

                    Text(
                        text = "|",
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.border.secondary
                    )

                    Text(
                        text = bottle.mbti,
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.secondary
                    )
                }
            }

            CoilImage(
                modifier = Modifier
                    .size(size = 48.dp)
                    .clip(shape = CircleShape)
                    .cloudy(
                        radius = 5,
                        graphicsLayer = graphicsLayer
                    ),
                imageModel = { bottle.imageUrl },
                previewPlaceholder = painterResource(id = R.drawable.sample_image),
                loading = { _ ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = BottlesTheme.color.icon.secondary)
                            .clip(shape = CircleShape),
                    )
                },
                failure = { _ ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = BottlesTheme.color.icon.secondary)
                            .clip(shape = CircleShape),
                    )
                },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                )
            )
        }
    }
}

@Deprecated("2차 MVP로 인한 디자인 변경. 2024-09-30")
@Composable
fun BottleItem(
    modifier: Modifier = Modifier,
    bottle: Bottle,
    onClickItem: () -> Unit,
    graphicsLayer: GraphicsLayer = rememberGraphicsLayer()
) {
    Column(
        modifier = modifier
            .background(
                color = BottlesTheme.color.container.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(BottlesTheme.padding.medium)
            .debounceNoRippleClickable(onClick = onClickItem),
        verticalArrangement = Arrangement.spacedBy(space = BottlesTheme.spacing.small)
    ) {
        if (bottle.lastActivatedAt != null) {
            EtcText(text = bottle.lastActivatedAt)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = bottle.name,
                        style = BottlesTheme.typography.subTitle2,
                        color = BottlesTheme.color.text.secondary
                    )

                    if (!bottle.isRead) {
                        val circleColor = BottlesTheme.color.icon.update

                        Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.doubleExtraSmall))
                        Canvas(modifier = Modifier.size(4.dp)) {
                            drawCircle(color = circleColor)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${bottle.age}세",
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.tertiary
                    )

                    Text(
                        text = "|",
                        style = TextStyle(
                            fontFamily = wantedSansStd,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            lineHeight = 12.sp * 1.5f,
                        ),
                        color = BottlesTheme.color.border.secondary
                    )

                    Text(
                        text = bottle.mbti,
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.tertiary
                    )

                    Text(
                        text = "|",
                        style = TextStyle(
                            fontFamily = wantedSansStd,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            lineHeight = 12.sp * 1.5f,
                        ),
                        color = BottlesTheme.color.border.secondary
                    )

                    Text(
                        text = "${bottle.personality[0]}, ${bottle.personality[1]}, ${bottle.personality[2]}",
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.tertiary
                    )
                }
            }

            CoilImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .cloudy(
                        radius = 5,
                        graphicsLayer = graphicsLayer
                    ),
                imageModel = { bottle.imageUrl },
                previewPlaceholder = painterResource(id = R.drawable.sample_image),
                loading = { _ ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = BottlesTheme.color.icon.secondary)
                            .clip(shape = CircleShape),
                    )
                },
                failure = { _ ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = BottlesTheme.color.icon.secondary)
                            .clip(shape = CircleShape),
                    )
                },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottleItemPreview() {
    BottlesTheme {
        BottleItem(
            bottle = Bottle(
                id = 0L,
                imageUrl = "",
                name = "냥냥이",
                age = 15,
                mbti = "ESTJ",
                personality = listOf("상냥한", "대담한", "낭만적인"),
                isRead = false,
                lastActivatedAt = "00분 전",
                lastStatus = "연락처를 공유했어요"
            ),
            onClickItem = { /*TODO*/ }
        )
    }
}