package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                shape = BottlesTheme.shape.radius12
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = BottlesTheme.shape.radius12
            )
            .clickable(onClick = onClickButton)
            .padding(BottlesTheme.padding.padding16),
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

/*==============Preview==============*/

@Preview(showBackground = true)
@Composable
private fun BottlesDropDownButtonPreview() {
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