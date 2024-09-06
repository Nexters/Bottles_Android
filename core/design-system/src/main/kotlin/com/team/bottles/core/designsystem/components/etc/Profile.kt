package com.team.bottles.core.designsystem.components.etc

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.cloudy.cloudy
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

enum class ProfileImageType(val size: Dp) {
    SM(size = 40.dp),
    LG(size = 80.dp),
    ;
}

@Composable
fun BottlesProfileEdit(
    modifier: Modifier = Modifier,
    onClickImage: () -> Unit,
    imageUrl: String,
    profileImageType: ProfileImageType = ProfileImageType.LG,
) {
    Box(
        modifier = modifier
            .debounceNoRippleClickable(onClick = onClickImage)
    ) {
        BottlesProfile(
            imageUrl = imageUrl,
            profileImageType = profileImageType,
            isBlur = false
        )
        Box(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .offset(x = 5.dp)
                .background(
                    color = BottlesTheme.color.container.enabledPrimary,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = BottlesTheme.color.border.enabled,
                    shape = CircleShape
                )
                .clip(shape = CircleShape)
                .padding(all = 6.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil_12),
                contentDescription = null,
                tint = BottlesTheme.color.icon.primary
            )
        }
    }
}

@Composable
fun BottlesProfile(
    modifier: Modifier = Modifier,
    imageUrl: String,
    profileImageType: ProfileImageType,
    isBlur: Boolean = true
) {
    CoilImage(
        modifier = modifier
            .size(size = profileImageType.size)
            .clip(shape = CircleShape)
            .then(
                if (isBlur) {
                    Modifier.cloudy(radius = 5)
                } else {
                    Modifier
                }
            ),
        imageModel = { imageUrl },
        previewPlaceholder = painterResource(id = R.drawable.sample_image),
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop
        ),
        loading = {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = BottlesTheme.color.icon.secondary,
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape)
            )
        },
        failure = {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = BottlesTheme.color.icon.secondary,
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape)
            )
        }
    )
}

@Preview
@Composable
private fun ProfileEditPreview() {
    BottlesTheme {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            BottlesProfileEdit(
                onClickImage = { /*TODO*/ },
                imageUrl = ""
            )
            BottlesProfile(
                imageUrl = "",
                profileImageType = ProfileImageType.LG
            )
            BottlesProfile(
                imageUrl = "",
                profileImageType = ProfileImageType.SM
            )
        }
    }
}