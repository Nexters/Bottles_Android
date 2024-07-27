package com.team.bottles.core.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

enum class OutlinedButtonState {
    ENABLED,
    SELECTED,
    DISABLED,
    ;
}

/*==============Preview==============*/

private data class RowButton(
    val isSelectedCategory: Category = Category.INTRO,
    val isMatching: Boolean = false
)

private enum class Category(val category: String) {
    INTRO("소개"),
    LETTER("편지"),
    MATCHING("매칭")
}

@Preview(widthDp = 500, showBackground = true)
@Composable
private fun BottleHistoryPreview() {
    BottlesTheme {
        val uiState by remember { mutableStateOf(RowButton()) }

        Row {
            Category.entries.forEach { entry ->
                OutlinedButton(
                    modifier = Modifier
                        .width(53.dp)
                        .height(36.dp),
                    shape = BottlesTheme.shape.radius8,
                    text = entry.category,
                    onClick = {},
                    state = when {
                        entry == Category.MATCHING && !uiState.isMatching -> OutlinedButtonState.DISABLED
                        entry == uiState.isSelectedCategory -> OutlinedButtonState.SELECTED
                        else -> OutlinedButtonState.ENABLED
                    }
                )
            }
        }
    }
}

@Preview(widthDp = 500, showBackground = true)
@Composable
private fun OutlinedButtonPreview() {
    BottlesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .width(53.dp)
                        .height(36.dp),
                    shape = BottlesTheme.shape.radius8,
                    state = OutlinedButtonState.ENABLED,
                    text = "Text",
                    onClick = {}
                )
                OutlinedButton(
                    modifier = Modifier
                        .width(53.dp)
                        .height(36.dp),
                    shape = BottlesTheme.shape.radius8,
                    state = OutlinedButtonState.SELECTED,
                    text = "Text",
                    onClick = {}
                )
                OutlinedButton(
                    modifier = Modifier
                        .width(53.dp)
                        .height(36.dp),
                    shape = BottlesTheme.shape.radius8,
                    state = OutlinedButtonState.DISABLED,
                    text = "Text",
                    onClick = {}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                OutlinedButtonWithIcon(
                    modifier = Modifier
                        .width(77.dp)
                        .height(36.dp),
                    state = OutlinedButtonState.ENABLED,
                    text = "Text",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right_16),
                            contentDescription = null
                        )
                    },
                    onClick = {}
                )
                OutlinedButtonWithIcon(
                    modifier = Modifier
                        .width(77.dp)
                        .height(36.dp),
                    state = OutlinedButtonState.SELECTED,
                    text = "Text",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right_16),
                            contentDescription = null
                        )
                    },
                    onClick = {}
                )
                OutlinedButtonWithIcon(
                    modifier = Modifier
                        .width(77.dp)
                        .height(36.dp),
                    state = OutlinedButtonState.DISABLED,
                    text = "Text",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right_16),
                            contentDescription = null
                        )
                    },
                    onClick = {}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .width(158.dp)
                        .height(56.dp),
                    state = OutlinedButtonState.ENABLED,
                    text = "Text",
                    onClick = {}
                )
                OutlinedButton(
                    modifier = Modifier
                        .width(158.dp)
                        .height(56.dp),
                    state = OutlinedButtonState.SELECTED,
                    text = "Text",
                    onClick = {}
                )
                OutlinedButton(
                    modifier = Modifier
                        .width(158.dp)
                        .height(56.dp),
                    state = OutlinedButtonState.DISABLED,
                    text = "Text",
                    onClick = {}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                OutlinedButtonWithImage(
                    text = "Text",
                    onClick = {},
                    state = OutlinedButtonState.ENABLED,
                    image = null,
                    placeholder = painterResource(id = R.drawable.sample_image)
                )
                OutlinedButtonWithImage(
                    text = "Text",
                    onClick = {},
                    state = OutlinedButtonState.SELECTED,
                    image = null,
                    placeholder = painterResource(id = R.drawable.sample_image)
                )
                OutlinedButtonWithImage(
                    text = "Text",
                    onClick = {},
                    state = OutlinedButtonState.DISABLED,
                    image = null,
                    placeholder = painterResource(id = R.drawable.sample_image)
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .width(328.dp)
                    .height(56.dp),
                state = OutlinedButtonState.ENABLED,
                text = "Text",
                onClick = {}
            )
            OutlinedButton(
                modifier = Modifier
                    .width(328.dp)
                    .height(56.dp),
                state = OutlinedButtonState.SELECTED,
                text = "Text",
                onClick = {}
            )
            OutlinedButton(
                modifier = Modifier
                    .width(328.dp)
                    .height(56.dp),
                state = OutlinedButtonState.DISABLED,
                text = "Text",
                onClick = {}
            )
        }
    }
}