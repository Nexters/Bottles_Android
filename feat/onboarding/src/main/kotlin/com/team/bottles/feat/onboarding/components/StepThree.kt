package com.team.bottles.feat.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.designsystem.R

@Composable
internal fun StepThree() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .offset(x = (-13).dp)
                    .clip(shape = BottlesTheme.shape.medium),
                painter = painterResource(id = R.drawable.onboarding_girl),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.small))

            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .offset(x = 13.dp)
                    .clip(shape = BottlesTheme.shape.medium),
                painter = painterResource(id = R.drawable.onboarding_boy),
                contentDescription = null
            )
        }

        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 112.dp,
                    start = 54.dp,
                    end = 54.dp
                )
                .shadow(elevation = 25.dp),
            painter = painterResource(id = R.drawable.onboarding_share_photo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}