package com.team.bottles.feat.sandbeach.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopupWithButton
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun RequireIntroduction(
    onClickIntroduction: () -> Unit
) {
    Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))
    BottlesBalloonPopupWithButton(
        text = "자기소개 작성 후 매칭을 받을 수 있어요",
        buttonText = "자기소개 작성하기",
        onClick = onClickIntroduction
    )
}