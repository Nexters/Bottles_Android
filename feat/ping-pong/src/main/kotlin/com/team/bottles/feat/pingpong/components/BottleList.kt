package com.team.bottles.feat.pingpong.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottleItem
import com.team.bottles.core.ui.model.Bottle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun BottleList(
    modifier: Modifier = Modifier,
    bottles: ImmutableList<Bottle>,
    onClickItem: (Bottle) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = BottlesTheme.spacing.extraLarge,
            bottom = BottlesTheme.spacing.extraLarge,
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
            )
        }
    }
}

@Preview
@Composable
private fun BottleListPreview() {
    BottlesTheme {
        BottleList(
            bottles = Bottle.exampleBottleList().toImmutableList(),
            onClickItem = {}
        )
    }
}