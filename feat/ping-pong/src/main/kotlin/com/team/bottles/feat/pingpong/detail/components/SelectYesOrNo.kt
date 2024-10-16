package com.team.bottles.feat.pingpong.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.buttons.BottlesOutlinedButtonWithImage
import com.team.bottles.core.designsystem.components.buttons.BottlesSolidButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonState
import com.team.bottles.core.designsystem.components.buttons.SolidButtonType
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.pingpong.detail.mvi.ShareSelectButtonState

@Composable
internal fun SelectYesOrNo(
    onClickAgree: () -> Unit,
    onClickReject: () -> Unit,
    onClickComplete: (Boolean) -> Unit,
    selectState: ShareSelectButtonState,
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.small
        )
    ) {
        BottlesOutlinedButtonWithImage(
            modifier = Modifier.weight(1f),
            text = "네! 좋아요",
            image = R.drawable.illustration_yes,
            onClick = onClickAgree,
            state = if (selectState == ShareSelectButtonState.LIKE_SHARE) OutlinedButtonState.SELECTED
            else OutlinedButtonState.ENABLED
        )
        BottlesOutlinedButtonWithImage(
            modifier = Modifier.weight(1f),
            text = "아니요",
            image = R.drawable.illustration_no,
            onClick = onClickReject,
            state = if (selectState == ShareSelectButtonState.HATE_SHARE) OutlinedButtonState.SELECTED
            else OutlinedButtonState.ENABLED
        )
    }

    BottlesSolidButton(
        modifier = Modifier.fillMaxWidth(),
        buttonType = SolidButtonType.MD,
        text = "선택 완료",
        onClick = { onClickComplete(selectState == ShareSelectButtonState.LIKE_SHARE) },
        enabled = selectState != ShareSelectButtonState.NONE
    )
}