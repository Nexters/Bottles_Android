package com.team.bottles.feat.bottle.bottlebox.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.EmptyBottles
import com.team.bottles.core.ui.model.Bottle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun BottleBoxContents(
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

@Preview(showBackground = true)
@Composable
private fun BottleBoxContentsPreview() {
    BottlesTheme {
        BottleBoxContents(
            bottles = Bottle.exampleBottleBox().toImmutableList(),
            emptyText = "아직 보관중인\n보틀이 없어요!",
            emptyImage = R.drawable.illustration_basket,
            onClickItem = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottleBoxEmptyContentsPreview() {
    BottlesTheme {
        BottleBoxContents(
            bottles = listOf<Bottle>().toImmutableList(),
            emptyText = "아직 보관중인\n보틀이 없어요!",
            emptyImage = R.drawable.illustration_basket,
            onClickItem = {}
        )
    }
}