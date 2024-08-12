package com.team.bottles.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun CardQuit(
    modifier: Modifier = Modifier,
    userName: String,
    deleteDay: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BottlesTheme.color.container.primary,
                shape = BottlesTheme.shape.extraLarge
            )
            .clip(shape = BottlesTheme.shape.extraLarge)
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.primary,
                shape = BottlesTheme.shape.extraLarge,
            )
            .padding(
                horizontal = BottlesTheme.spacing.medium,
                vertical = BottlesTheme.spacing.extraLarge
            ),
    ) {
        Text(
            text = stringResource(
                id = R.string.finished_conversation_card_quit,
                formatArgs = arrayOf(userName)
            ),
            style = BottlesTheme.typography.subTitle1,
            color = BottlesTheme.color.text.primary
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraSmall))

        Text(
            text = stringResource(
                id = R.string.delete_day_card_quit,
                formatArgs = arrayOf(deleteDay)
            ),
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.tertiary
        )

        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

        CoilImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(size = 200.dp),
            imageModel = { R.drawable.illustration_loudspeaker },
            previewPlaceholder = painterResource(id = R.drawable.illustration_loudspeaker),
        )
    }
}

@Preview
@Composable
private fun CardQuitPreview() {
    BottlesTheme {
        CardQuit(
            userName = "뇽뇽이",
            deleteDay = 3
        )
    }
}