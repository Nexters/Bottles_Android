package com.team.bottles.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun EmptyBottles(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes image: Int
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = BottlesTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = BottlesTheme.typography.title1,
            color = BottlesTheme.color.text.primary
        )
        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
        CoilImage(
            modifier = Modifier.sizeIn(
                minHeight = 250.dp,
                minWidth = 250.dp,
                maxHeight = 500.dp,
                maxWidth = 500.dp,
            ),
            imageModel = { image },
            previewPlaceholder = painterResource(id = R.drawable.illustration_basket)
        )
    }
}

@Preview(showBackground = true, heightDp = 480)
@Composable
private fun EmptyBottlesPreview() {
    BottlesTheme {
        Column{
            EmptyBottles(
                text = "아직 보관중인\n보틀이 없어요!",
                image = R.drawable.illustration_basket
            )
        }
    }
}