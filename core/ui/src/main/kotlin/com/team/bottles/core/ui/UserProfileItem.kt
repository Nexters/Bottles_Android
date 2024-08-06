package com.team.bottles.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.etc.chips.EtcText
import com.team.bottles.core.designsystem.foundation.wantedSansStd
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun UserProfileItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    age: Int,
    mbti: String,
    personality: List<String>,
    isRead: Boolean,
    remainingTime: String? = null,
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
            .padding(BottlesTheme.padding.medium),
        verticalArrangement = Arrangement.spacedBy(space = BottlesTheme.spacing.small)
    ) {
        if (remainingTime != null) {
            EtcText(text = remainingTime)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        style = BottlesTheme.typography.subTitle2,
                        color = BottlesTheme.color.text.secondary
                    )

                    if (!isRead) {
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
                        text = "${age}세",
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
                        text = mbti,
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
                        text = "${personality[0]}, ${personality[1]}, ${personality[2]}",
                        style = BottlesTheme.typography.caption,
                        color = BottlesTheme.color.text.tertiary
                    )
                }
            }

            CoilImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                imageModel = { imageUrl },
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
                success = { _, painter ->
                    Image(
                        painter = painter,
                        contentDescription = null
                    )
                },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                )
            )
        }
    }
}

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun UserProfileItemPreview() {
    BottlesTheme {
        val listState = rememberLazyListState()
        val userList = listOf(
            UserInfo(
                id = 1,
                image = "https://avatars.githubusercontent.com/u/54674781?v=4",
                name = "냥냥이",
                age = 15,
                remainingTime = null,
                mbti = "INTP",
                personality = listOf("적극적인", "열적정인", "예의바른"),
                isRead = true
            ),
            UserInfo(
                id = 2,
                name = "뇽뇽이",
                age = 15,
                remainingTime = null,
                mbti = "INTP",
                personality = listOf("적극적인", "열적정인", "예의바른"),
                isRead = false
            ),
            UserInfo(
                id = 3,
                name = "냥냥이",
                age = 15,
                remainingTime = "1시간 후 사라져요",
                mbti = "INTP",
                personality = listOf("적극적인", "열적정인", "예의바른"),
                isRead = true
            )
        )

        LazyColumn(
            state = listState,
            contentPadding = BottlesTheme.padding.medium,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(
                items = userList,
                key = { userInfo -> userInfo.id }
            ) { userInfo ->
                UserProfileItem(
                    imageUrl = userInfo.image,
                    name = userInfo.name,
                    age = userInfo.age,
                    mbti = userInfo.mbti,
                    remainingTime = userInfo.remainingTime,
                    personality = userInfo.personality,
                    isRead = userInfo.isRead
                )
            }
        }
    }
}

private data class UserInfo(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val age: Int = 0,
    val remainingTime: String? = null,
    val mbti: String = "",
    val personality: List<String> = emptyList(),
    val isRead: Boolean = false,
)