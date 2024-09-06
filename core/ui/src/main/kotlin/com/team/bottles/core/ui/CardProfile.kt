@file:OptIn(ExperimentalLayoutApi::class)

package com.team.bottles.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesOutLinedButton
import com.team.bottles.core.designsystem.components.buttons.OutlinedButtonType
import com.team.bottles.core.designsystem.components.cards.BottlesCard
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.model.UserKeyPoint

@Composable
fun CardProfile(
    modifier: Modifier = Modifier,
    keyPoints: List<UserKeyPoint>,
) {
    BottlesCard(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraLarge
        )
    ) {
        keyPoints.forEach { keypoint ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    space = BottlesTheme.spacing.small
                ),
            ) {
                Text(
                    text = keypoint.subtitle,
                    style = BottlesTheme.typography.subTitle2,
                    color = BottlesTheme.color.text.tertiary
                )
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    keypoint.properties.forEach { property ->
                        BottlesOutLinedButton(
                            text = property,
                            onClick = { },
                            buttonType = OutlinedButtonType.SM,
                            contentHorizontalPadding = 12.5f.dp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardProfilePreview() {
    BottlesTheme {
        CardProfile(keyPoints = UserKeyPoint.exampleUerKeyPoints())
    }
}