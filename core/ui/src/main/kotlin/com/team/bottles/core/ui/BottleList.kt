package com.team.bottles.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.model.Bottle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun BottleContents(
    bottles: ImmutableList<Bottle>,
    emptyText: String,
    @DrawableRes emptyImage: Int,
    onClickItem: (Bottle) -> Unit
) {
    if (bottles.isEmpty()) {
        EmptyBottles(
            text = emptyText,
            image = emptyImage
        )
    } else {
        BottleList(
            bottles = bottles,
            onClickItem = onClickItem
        )
    }
}

@Composable
fun BottleList(
    modifier: Modifier = Modifier,
    bottles: ImmutableList<Bottle>,
    onClickItem: (Bottle) -> Unit
) {
    val graphicsLayer = rememberGraphicsLayer()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = BottlesTheme.spacing.extraLarge,
            bottom = BottlesTheme.spacing.large,
            start = BottlesTheme.spacing.medium,
            end = BottlesTheme.spacing.medium
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.medium
        )
    ) {
        items(
            items = bottles,
            key = { bottle -> bottle.id }
        ) { bottle ->
            BottleItem(
                bottle = bottle,
                onClickItem = { onClickItem(bottle) },
                graphicsLayer = graphicsLayer
            )
        }
    }
}

@Preview
@Composable
private fun BottleListPreview() {
    BottlesTheme {
        BottleList(
            bottles = Bottle.exampleBottleBox().toImmutableList(),
            onClickItem = {}
        )
    }
}