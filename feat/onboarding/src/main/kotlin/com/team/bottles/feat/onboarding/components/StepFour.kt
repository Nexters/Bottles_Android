package com.team.bottles.feat.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R

@Composable
internal fun StepFour() {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        painter = painterResource(id = R.drawable.illustration_bottle),
        contentDescription = null,
    )
}