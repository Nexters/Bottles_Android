package com.team.bottles.feat.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
internal fun Symbol() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(height = 52.dp))
        Icon(
            painter = painterResource(id = R.drawable.bottle_logo),
            contentDescription = null,
            tint = BottlesTheme.color.text.quaternary
        )
        Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.branding_message),
            style = BottlesTheme.typography.title3,
            color = BottlesTheme.color.text.quaternary
        )
    }
}

@Preview
@Composable
private fun SymbolPreview() {
    BottlesTheme {
        Symbol()
    }
}