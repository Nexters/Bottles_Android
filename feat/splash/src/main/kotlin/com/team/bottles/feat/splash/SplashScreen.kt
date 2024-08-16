package com.team.bottles.feat.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF615EFA)),
        verticalArrangement = Arrangement.spacedBy(
            space = (-12).dp, Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(size = 120.dp),
            painter = painterResource(id = R.drawable.bottle_symbol),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(id = R.drawable.bottle_logo_white),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    BottlesTheme {
        SplashScreen()
    }
}