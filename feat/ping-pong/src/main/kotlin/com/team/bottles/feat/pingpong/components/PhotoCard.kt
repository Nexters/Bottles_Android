package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesLetterDropDownButton
import com.team.bottles.core.designsystem.components.buttons.BottlesOutlinedButtonWithImage
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonState
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.bottle.model.PhotoCardStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhoto
import com.team.bottles.core.ui.PartnerBubble
import com.team.bottles.core.ui.UserBubble
import com.team.bottles.feat.pingpong.mvi.PhotoShareSelectState

@Composable
internal fun LazyItemScope.PhotoCard(
    photo: PingPongPhoto,
    onClickPhotoCard: () -> Unit,
    onClickLikeShare: () -> Unit,
    onClickHateShare: () -> Unit,
    onClickShareProfilePhoto: () -> Unit,
    isExpanded: Boolean,
    isEnabled: Boolean,
    selectState: PhotoShareSelectState
) {
    BottlesLetterDropDownButton(
        onClickButton = onClickPhotoCard,
        text = "사진 공개",
        isExpanded = isExpanded,
        isEnabled = isEnabled
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraLarge
            )
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = BottlesTheme.color.border.secondary
            )

            if (photo.photoCardStatus != PhotoCardStatus.BOTH_AGREE &&
                photo.photoCardStatus != PhotoCardStatus.NONE
            ) {
                Text(
                    text = "나의 프로필 사진을 공유할까요?",
                    style = BottlesTheme.typography.subTitle2,
                    color = BottlesTheme.color.text.secondary
                )
            }

            when (photo.photoCardStatus) {
                PhotoCardStatus.REQUIRE_SELECT -> {
                    SelectYesOrNo(
                        onClickLikeShare = onClickLikeShare,
                        onClickHateShare = onClickHateShare,
                        onClickShareProfilePhoto = onClickShareProfilePhoto,
                        selectState = selectState
                    )
                }
                PhotoCardStatus.BOTH_AGREE -> {
                    Done(
                        otherImageUrl = photo.otherImageUrl,
                        myImageUrl = photo.myImageUrl
                    )
                }
                PhotoCardStatus.MY_REJECT -> MyReject()
                PhotoCardStatus.OTHER_REJECT -> PartnerReject()
                PhotoCardStatus.WAITING_OTHER_ANSWER -> HoldOtherAnswer()
                PhotoCardStatus.NONE -> { }
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
private fun PartnerReject() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        PartnerBubble(text = "공유를 거절했어요")
        PartnerBubble(text = "상대방이 대화를 중단했어요")
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
private fun ColumnScope.SelectYesOrNo(
    onClickLikeShare: () -> Unit,
    onClickHateShare: () -> Unit,
    onClickShareProfilePhoto: () -> Unit,
    selectState: PhotoShareSelectState,
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        BottlesOutlinedButtonWithImage(
            modifier = Modifier.weight(1f),
            text = "네! 좋아요",
            image = R.drawable.illustration_yes,
            onClick = onClickLikeShare,
            state = if (selectState == PhotoShareSelectState.LIKE_SHARE) OutlinedButtonState.SELECTED
            else OutlinedButtonState.ENABLED
        )
        BottlesOutlinedButtonWithImage(
            modifier = Modifier.weight(1f),
            text = "아니요",
            image = R.drawable.illustration_no,
            onClick = onClickHateShare,
            state = if (selectState == PhotoShareSelectState.HATE_SHARE) OutlinedButtonState.SELECTED
            else OutlinedButtonState.ENABLED
        )
    }

    BottlesSolidButton(
        modifier = Modifier.fillMaxWidth(),
        buttonType = SolidButtonType.MD,
        text = "선택 완료",
        onClick = onClickShareProfilePhoto,
        enabled = selectState != PhotoShareSelectState.NONE
    )
}

@Composable
private fun ColumnScope.Done(
    otherImageUrl: String?,
    myImageUrl: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        repeat(2) { count ->
            CoilImage(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .background(
                        color = BottlesTheme.color.icon.secondary,
                        shape = RoundedCornerShape(
                            topStart = if (count == 0) BottlesTheme.spacing.medium else 0.dp,
                            topEnd = BottlesTheme.spacing.medium,
                            bottomStart = BottlesTheme.spacing.medium,
                            bottomEnd = if (count == 1) BottlesTheme.spacing.medium else 0.dp
                        )
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            topEnd = BottlesTheme.spacing.medium,
                            bottomStart = BottlesTheme.spacing.medium,
                            bottomEnd = BottlesTheme.spacing.medium
                        )
                    ),
                previewPlaceholder = painterResource(id = R.drawable.sample_image),
                imageModel = { if (count == 0) otherImageUrl else myImageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                ),
                loading = {
                    Box(modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            color = BottlesTheme.color.icon.secondary,
                            shape = RoundedCornerShape(
                                topStart = BottlesTheme.spacing.medium,
                                topEnd = BottlesTheme.spacing.medium,
                                bottomStart = BottlesTheme.spacing.medium,
                            )
                        )
                        .clip(
                            shape = RoundedCornerShape(
                                topEnd = BottlesTheme.spacing.medium,
                                bottomStart = BottlesTheme.spacing.medium,
                                bottomEnd = BottlesTheme.spacing.medium
                            )
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 1650)
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
            PhotoCardStatus.entries.forEach { photoCardStatus ->
                if (photoCardStatus != PhotoCardStatus.NONE) {
                    item {
                        PhotoCard(
                            photo = PingPongPhoto(
                                photoCardStatus = photoCardStatus
                            ),
                            onClickPhotoCard = { /*TODO*/ },
                            onClickLikeShare = { /*TODO*/ },
                            onClickHateShare = { /*TODO*/ },
                            onClickShareProfilePhoto = {},
                            isExpanded = true,
                            isEnabled = true,
                            selectState = PhotoShareSelectState.NONE
                        )
                    }
                }
            }
        }
    }
}