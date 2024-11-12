package com.team.bottles.core.designsystem.components.etc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesUserInfo(
    modifier: Modifier = Modifier,
    onClickImage: () -> Unit,
    imageUrl: String,
    userName: String,
    userAge: Int,
    profileImageType: ProfileImageType = ProfileImageType.LG
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottlesProfileEdit(
            imageUrl = imageUrl,
            profileImageType = profileImageType,
            onClickImage = onClickImage
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

@Composable
fun BottlesUserInfo(
    modifier: Modifier = Modifier,
    imageUrl: String,
    userName: String,
    userAge: Int,
    isBlur: Boolean = true,
    profileImageType: ProfileImageType = ProfileImageType.LG,
    graphicsLayer: GraphicsLayer = rememberGraphicsLayer()
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottlesProfile(
            imageUrl = imageUrl,
            profileImageType = profileImageType,
            isBlur = isBlur,
            graphicsLayer = graphicsLayer
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
        BottlesUserInfo(
            imageUrl = "",
            userName = "냥냥이",
            userAge = 15,
            graphicsLayer = rememberGraphicsLayer()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoEditPreview() {
    BottlesTheme {
        BottlesUserInfo(
            imageUrl = "",
            userName = "냥냥이",
            userAge = 15,
            onClickImage = {}
        )
    }
}