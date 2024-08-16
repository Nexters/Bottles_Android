package com.team.bottles.feat.sandbeach

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.etc.chips.BottlesChip
import com.team.bottles.core.designsystem.components.etc.chips.EtcText
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopup
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopupWithButton
import com.team.bottles.core.designsystem.modifier.debounceNoRippleClickable
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.sandbeach.component.BottleStatusMessage
import com.team.bottles.feat.sandbeach.component.InArrivedBottle
import com.team.bottles.feat.sandbeach.component.InBottleBox
import com.team.bottles.feat.sandbeach.component.NoneBottle
import com.team.bottles.feat.sandbeach.component.RequireIntroduction
import com.team.bottles.feat.sandbeach.mvi.BottleStatus
import com.team.bottles.feat.sandbeach.mvi.SandBeachIntent
import com.team.bottles.feat.sandbeach.mvi.SandBeachUiState

@Composable
internal fun SandBeachScreen(
    uiState: SandBeachUiState,
    onIntent: (SandBeachIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottlesTopBar(
            centerContents = {
                Icon(
                    painter = painterResource(id = R.drawable.bottle_logo),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

        BottleStatusMessage(
            bottleStatus = uiState.bottleStatus,
            newBottleValue = uiState.newBottleValue,
            bottleBoxValue = uiState.bottleBoxValue
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState.bottleStatus) {
                BottleStatus.REQUIRE_INTRODUCTION -> {
                    RequireIntroduction(
                        onClickIntroduction = { onIntent(SandBeachIntent.ClickCreateIntroductionButton) }
                    )
                }
                BottleStatus.IN_ARRIVED_BOTTLE -> InArrivedBottle(bottleValue = uiState.newBottleValue)
                BottleStatus.IN_BOTTLE_BOX -> InBottleBox()
                BottleStatus.NONE_BOTTLE -> NoneBottle(afterArrivedTime = uiState.afterArrivedTime)
            }

            Image(
                modifier = Modifier
                    .sizeIn(
                        maxWidth = 430.dp,
                        maxHeight = 430.dp,
                        minHeight = 280.dp,
                        minWidth = 280.dp
                    )
                    .debounceNoRippleClickable(
                        debounceTime = 200L,
                        onClick = { onIntent(SandBeachIntent.ClickSandBeach) }
                    ),
                painter = painterResource(
                    id = if (uiState.bottleStatus == BottleStatus.NONE_BOTTLE) R.drawable.illustration_island_bottle_false
                    else R.drawable.illustration_island_bottle_true),
                contentDescription = null
            )
        }
    }
}

@Preview(widthDp = 720, heightDp = 850)
@Preview(widthDp = 360, heightDp = 550)
@Preview(widthDp = 360, heightDp = 850)
@Composable
private fun SandBeachScreenPreview() {
    BottlesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.bg_sand_beach),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            SandBeachScreen(
                uiState = SandBeachUiState(bottleStatus = BottleStatus.IN_ARRIVED_BOTTLE),
                onIntent = {}
            )
        }
    }
}