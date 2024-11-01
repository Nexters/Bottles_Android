package com.team.bottles.feat.pingpong.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesLetterDropDownButton
import com.team.bottles.core.designsystem.components.etc.chips.BottlesProgressChip
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PingPongPhotoStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotos
import com.team.bottles.core.ui.PartnerBubble
import com.team.bottles.core.ui.UserBubble
import com.team.bottles.feat.pingpong.detail.mvi.ShareSelectButtonState
import kotlinx.coroutines.launch

@Composable
internal fun PhotoCard(
    pingPongPhotos: PingPongPhotos,
    pingPongPhotoStatus: PingPongPhotoStatus,
    onClickPhotoCard: () -> Unit,
    onClickLikeSharePhoto: () -> Unit,
    onClickHateSharePhoto: () -> Unit,
    onClickShareProfilePhoto: (Boolean) -> Unit,
    isExpanded: Boolean,
    selectState: ShareSelectButtonState
) {
    BottlesLetterDropDownButton(
        onClickButton = onClickPhotoCard,
        text = "사진 공개",
        isExpanded = isExpanded,
        isEnabled = pingPongPhotoStatus != PingPongPhotoStatus.NONE
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = if (pingPongPhotoStatus == PingPongPhotoStatus.BOTH_AGREE){
                    BottlesTheme.spacing.large
                } else {
                    BottlesTheme.spacing.extraLarge
                }
            )
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = BottlesTheme.color.border.secondary
            )

            if (pingPongPhotoStatus != PingPongPhotoStatus.BOTH_AGREE) {
                Text(
                    text = "나의 프로필 사진을 공유할까요?",
                    style = BottlesTheme.typography.subTitle2,
                    color = BottlesTheme.color.text.secondary
                )
            }

            when (pingPongPhotoStatus) {
                PingPongPhotoStatus.REQUIRE_SELECT -> {
                    SelectYesOrNo(
                        onClickAgree = onClickLikeSharePhoto,
                        onClickReject = onClickHateSharePhoto,
                        onClickComplete = onClickShareProfilePhoto,
                        selectState = selectState
                    )
                }

                PingPongPhotoStatus.BOTH_AGREE -> {
                    Done(otherImageUrls = pingPongPhotos.otherImageUrls)
                }

                PingPongPhotoStatus.MY_REJECT -> MyReject()
                PingPongPhotoStatus.OTHER_REJECT -> PartnerReject()
                PingPongPhotoStatus.WAITING_OTHER_ANSWER -> HoldOtherAnswer()
                PingPongPhotoStatus.NONE -> {}
            }
        }
    }
}

@Composable
private fun HoldOtherAnswer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        UserBubble(text = "공유를 완료했어요")
        PartnerBubble(text = "상대방의 답변을 기다리고 있어요")
        PartnerBubble(text = "서로가 모두 공유에 동의한 경우에 공개돼요")
    }
}

@Composable
private fun MyReject() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        UserBubble(text = "공유를 거절했어요")
        PartnerBubble(text = "상대방에게 대화 중단을 알렸어요")
    }
}

@Composable
private fun ColumnScope.Done(
    modifier: Modifier = Modifier,
    otherImageUrls: List<String>,
) {
    val infinitePageCount = Int.MAX_VALUE
    val pagerState = rememberPagerState(initialPage = infinitePageCount / 2) { infinitePageCount }
    val scope = rememberCoroutineScope()

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSpacing = 20.dp
    ) { page ->
        val actualPage = page % otherImageUrls.size

        CoilImage(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(
                    shape = RoundedCornerShape(
                        topEnd = BottlesTheme.spacing.medium,
                        bottomEnd = BottlesTheme.spacing.medium,
                        bottomStart = BottlesTheme.spacing.medium
                    )
                ),
            imageModel = { otherImageUrls[actualPage] },
            previewPlaceholder = painterResource(id = R.drawable.sample_image)
        )
    }

    Row(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.doubleExtraLarge
        )
    ) {
        Icon(
            modifier = Modifier
                .noRippleClickable(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = otherImageUrls.size > 1
                ),
            painter = painterResource (id = R.drawable.ic_arrow_left_24),
            contentDescription = null,
            tint = if (otherImageUrls.size > 1) {
                BottlesTheme.color.icon.primary
            } else {
                BottlesTheme.color.icon.disabled
            }
        )

        BottlesProgressChip(
            currentPage = (pagerState.currentPage % otherImageUrls.size) + 1,
            maxPage = otherImageUrls.size
        )

        Icon(
            modifier = Modifier
                .noRippleClickable(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    enabled = otherImageUrls.size > 1
                ),
            painter = painterResource (id = R.drawable.ic_arrow_right_24),
            contentDescription = null,
            tint = if (otherImageUrls.size > 1) {
                BottlesTheme.color.icon.primary
            } else {
                BottlesTheme.color.icon.disabled
            }
        )
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun PhotoCardPreview() {
    BottlesTheme {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(
                start = BottlesTheme.spacing.medium,
                end = BottlesTheme.spacing.medium,
                top = BottlesTheme.spacing.doubleExtraLarge,
                bottom = BottlesTheme.spacing.extraLarge
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PingPongPhotoStatus.entries.forEach { photoCardStatus ->
                item {
                    PhotoCard(
                        pingPongPhotos = PingPongPhotos(otherImageUrls = listOf("", "", "")),
                        pingPongPhotoStatus = photoCardStatus,
                        onClickPhotoCard = { /*TODO*/ },
                        onClickLikeSharePhoto = {},
                        onClickHateSharePhoto = {},
                        onClickShareProfilePhoto = {},
                        isExpanded = true,
                        selectState = ShareSelectButtonState.NONE
                    )
                }
            }
        }
    }
}