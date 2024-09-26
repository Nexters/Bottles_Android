package com.team.bottles.feat.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R

@Composable
internal fun StepTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 38.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            painter = painterResource(id = R.drawable.onboarding_qna_1),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Image(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 25.dp),
            painter = painterResource(id = R.drawable.onboarding_qna_2),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}