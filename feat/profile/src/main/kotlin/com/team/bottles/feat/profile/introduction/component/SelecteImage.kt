package com.team.bottles.feat.profile.introduction.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.feat.profile.introduction.mvi.IntroductionIntent

@Composable
internal fun SelectImageCard(
    imageUri: Uri?,
    onIntent: (IntroductionIntent) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onIntent(IntroductionIntent.ClickPhoto(uri = uri))
        }
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = BottlesTheme.color.onContainer.enabledSecondary,
                shape = BottlesTheme.shape.radius16
            )
            .clip(shape = BottlesTheme.shape.radius16)
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.enabled,
                shape = BottlesTheme.shape.radius16
            )
            .clickable(
                onClick = { launcher.launch("image/*") }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { imageUri },
                previewPlaceholder = painterResource(id = R.drawable.sample_image),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit
                )
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_24),
                contentDescription = null,
                tint = BottlesTheme.color.icon.primary
            )
        }
    }
}