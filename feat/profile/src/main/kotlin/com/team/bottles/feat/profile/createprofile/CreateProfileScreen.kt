package com.team.bottles.feat.profile.createprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileIntent
import com.team.bottles.feat.profile.createprofile.mvi.CreateProfileUiState

@Composable
internal fun CreateProfileScreen(
    uiState: CreateProfileUiState,
    onIntent: (CreateProfileIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "프로필 제작 화면")
        BottlesSolidButton(
            buttonType = SolidButtonType.XS,
            text = "메인 으로",
            onClick = { onIntent(CreateProfileIntent.ClickNextButton) },
            contentHorizontalPadding = 12.dp
        )
    }
}