package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

sealed class DropDownButtonState {

    data object Enabled : DropDownButtonState()

    data object Focused : DropDownButtonState()

    data object Active : DropDownButtonState()

    data object Disabled : DropDownButtonState()

}

/**
 * @GunHyung : 디자인 시스템에 정의 되어 있지 않은 색상은 Color.Transparent 로 정의
 */

@Composable
fun BottlesDefaultDropDownButton(
    modifier: Modifier = Modifier,
    text: String,
    onClickButton: () -> Unit,
    state: DropDownButtonState,
) {
    val containerColor = when (state) {
        is DropDownButtonState.Enabled -> BottlesTheme.color.container.enabledPrimary
        is DropDownButtonState.Focused -> BottlesTheme.color.container.focusedSecondary
        is DropDownButtonState.Active -> BottlesTheme.color.container.active
        is DropDownButtonState.Disabled -> Color.Transparent
    }
    val borderColor = when (state) {
        is DropDownButtonState.Enabled -> BottlesTheme.color.border.enabled
        is DropDownButtonState.Focused -> BottlesTheme.color.border.focusedPrimary
        is DropDownButtonState.Active -> BottlesTheme.color.border.active
        is DropDownButtonState.Disabled -> Color.Transparent
    }
    val textColor = when (state) {
        is DropDownButtonState.Enabled -> BottlesTheme.color.text.enabledTertiary
        is DropDownButtonState.Focused -> BottlesTheme.color.text.focusedPrimary
        is DropDownButtonState.Active -> BottlesTheme.color.text.activePrimary
        is DropDownButtonState.Disabled -> Color.Transparent
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = containerColor,
                shape = BottlesTheme.shape.small
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BottlesTheme.shape.small
            )
            .clickable(onClick = onClickButton)
            .padding(BottlesTheme.padding.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = textColor
        )
        Icon(
            painter = if (state is DropDownButtonState.Focused) painterResource(id = R.drawable.ic_up_24)
            else painterResource(id = R.drawable.ic_down_24),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
fun BottlesLetterDropDownButton(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    text: String,
    isExpanded: Boolean,
    isEnabled: Boolean,
    contents: @Composable () -> Unit,
) {
    val containerColor = when {
        isExpanded -> BottlesTheme.color.container.focusedSecondary
        isEnabled -> BottlesTheme.color.container.enabledPrimary
        !isEnabled -> BottlesTheme.color.container.disabledPrimary
        else -> Color.Transparent
    }
    val borderColor = when {
        isExpanded -> BottlesTheme.color.border.focusedPrimary
        isEnabled -> BottlesTheme.color.border.enabled
        !isEnabled -> BottlesTheme.color.border.disabled
        else -> Color.Transparent
    }
    val textColor = when {
        isExpanded -> BottlesTheme.color.text.focusedPrimary
        isEnabled -> BottlesTheme.color.text.enabledSecondary
        !isEnabled -> BottlesTheme.color.text.disabledSecondary
        else -> Color.Transparent
    }
    val iconColor = when {
        isExpanded -> BottlesTheme.color.icon.primary
        isEnabled -> BottlesTheme.color.icon.primary
        !isEnabled -> BottlesTheme.color.icon.disabled
        else -> Color.Transparent
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = containerColor,
                shape = BottlesTheme.shape.extraLarge
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BottlesTheme.shape.extraLarge
            )
            .clickable(
                onClick = onClickButton,
                enabled = isEnabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .padding(
                horizontal = BottlesTheme.spacing.medium,
                vertical = BottlesTheme.spacing.extraLarge
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = BottlesTheme.typography.subTitle1,
                color = textColor
            )
            Icon(
                painter = if (isExpanded) painterResource(id = R.drawable.ic_up_24)
                else painterResource(id = R.drawable.ic_down_24),
                contentDescription = null,
                tint = iconColor
            )
        }

        if(isExpanded) {
            Spacer(modifier = Modifier.height(BottlesTheme.spacing.extraLarge))
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically()
        ) {
            contents.invoke()
        }
    }
}

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun BottlesDefaultDropDownButtonPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottlesDefaultDropDownButton(
                text = "placeHolder",
                onClickButton = {},
                state = DropDownButtonState.Enabled
            )
            BottlesDefaultDropDownButton(
                text = "text",
                onClickButton = {},
                state = DropDownButtonState.Focused
            )
            BottlesDefaultDropDownButton(
                text = "text",
                onClickButton = {},
                state = DropDownButtonState.Active
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottlesLetterDropDownButtonPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            var isExpanded by remember { mutableStateOf(true) }

            BottlesLetterDropDownButton(
                text = "text",
                onClickButton = {},
                isExpanded = false,
                isEnabled = true,
                contents = {}
            )
            BottlesLetterDropDownButton(
                text = "사진공개",
                onClickButton = { isExpanded = !isExpanded },
                isExpanded = isExpanded,
                isEnabled = true,
                contents = {
                    Column {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = BottlesTheme.color.border.secondary
                        )

                        Spacer(modifier = Modifier.height(BottlesTheme.spacing.extraLarge))

                        Text(
                            text = "나의 프로필 사진을 공유할까요?",
                            style = BottlesTheme.typography.subTitle2,
                            color = BottlesTheme.color.text.secondary
                        )
                    }
                },
            )
            BottlesLetterDropDownButton(
                text = "text",
                onClickButton = {},
                isExpanded = false,
                isEnabled = false,
                contents = {}
            )
        }
    }
}