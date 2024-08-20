package com.team.bottles.core.designsystem.components.etc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    imageUrl: String,
    userName: String,
    userAge: Int
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            modifier = Modifier
                .size(80.dp)
                .clip(shape = CircleShape),
            imageModel = { imageUrl },
            previewPlaceholder = painterResource(id = R.drawable.sample_image),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop
            ),
            loading = {

            },
            failure = {

            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = BottlesTheme.spacing.extraSmall
            )
        ) {
            Text(
                text = userName,
                style = BottlesTheme.typography.subTitle1,
                color = BottlesTheme.color.text.secondary
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_spacing_bar_3_15),
                contentDescription = null,
                tint = BottlesTheme.color.border.secondary
            )
            Text(
                text = stringResource(
                    id = R.string.user_age_user_info,
                    formatArgs = arrayOf(userAge)
                ),
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoPreview() {
    BottlesTheme {
        UserInfo(
            imageUrl = "",
            userName = "냥냥이",
            userAge = 15
        )
    }
}