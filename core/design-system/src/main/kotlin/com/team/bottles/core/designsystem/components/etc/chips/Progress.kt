package com.team.bottles.core.designsystem.components.etc.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme


@Composable
fun BottlesProgressChip(
    modifier: Modifier = Modifier,
    currentPage: Int,
    maxPage: Int,
    backgroundColor: Color = BottlesTheme.color.container.secondary,
) {
    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = BottlesTheme.shape.extraSmall,
            )
            .padding(
                horizontal = BottlesTheme.spacing.extraSmall,
                vertical = BottlesTheme.spacing.doubleExtraSmall
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.doubleExtraSmall,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        Text(
            text = currentPage.toString(),
            style = BottlesTheme.typography.subTitle2,
            color = BottlesTheme.color.text.quinary
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_slash_5_14),
            contentDescription = null,
            tint = BottlesTheme.color.text.senary
        )
        Text(
            text = maxPage.toString(),
            style = BottlesTheme.typography.subTitle2,
            color = BottlesTheme.color.text.senary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressButtonPreview() {
    BottlesTheme {
        BottlesProgressChip(
            currentPage = 1,
            maxPage = 2
        )
    }
}