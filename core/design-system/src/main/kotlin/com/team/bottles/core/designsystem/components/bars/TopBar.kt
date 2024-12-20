package com.team.bottles.core.designsystem.components.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesTopBar(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    centerContents: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BottlesTheme.spacing.medium),
            horizontalArrangement = if (leadingIcon == null) Arrangement.End else Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }

            if (trailingIcon != null) {
                trailingIcon()
            }
        }

        if (centerContents != null) {
            centerContents()
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewTopBar() {
    BottlesTheme {
        Column {
            // Preview 1: Leading Icon만 있는 경우
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Preview 2: Leading Icon과 Trailing Icon만 있는 경우
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Preview 3: Leading Icon과 Text가 있는 경우
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                },
                centerContents = {
                    Text(
                        text = "Title",
                        style = BottlesTheme.typography.body,
                        color = BottlesTheme.color.text.secondary,
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Preview 4: Leading Icon, Text, Trailing Icon 모두 있는 경우
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                },
                centerContents = {
                    Text(
                        text = "Title",
                        style = BottlesTheme.typography.body,
                        color = BottlesTheme.color.text.secondary,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = "Back"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Preview 5: 아무것도 없는 경우
            BottlesTopBar()
        }
    }
}