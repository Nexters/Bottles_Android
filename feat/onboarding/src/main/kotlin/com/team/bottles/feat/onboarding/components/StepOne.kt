package com.team.bottles.feat.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R

@Composable
internal fun StepOne() {
    Box(modifier = Modifier.padding(horizontal = 46.dp)) {
        Image(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 40.dp))
                .border(
                    width = 10.dp,
                    color = Color(0xFF232328),
                    shape = RoundedCornerShape(size = 40.dp)
                ),
            painter = painterResource(id = R.drawable.onboarding_mockup),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}