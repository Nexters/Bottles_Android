package com.team.bottles.feat.sandbeach.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.popup.BottlesBalloonPopup

@Composable
internal fun NoneBottle(afterArrivedTime: Int) {
    Spacer(modifier = Modifier.height(97.dp))
    BottlesBalloonPopup(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF615EFA))) {
            append("$afterArrivedTime")
        }
        append("시간후 새로운 보틀이 도착해요")
    })
}