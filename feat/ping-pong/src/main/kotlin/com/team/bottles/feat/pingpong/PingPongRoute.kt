package com.team.bottles.feat.pingpong

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.pingpong.mvi.PingPongSideEffect

@Composable
internal fun PingPongRoute(
    viewModel: PingPongViewModel = hiltViewModel(),
    navigateToBottleBox: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is PingPongSideEffect.NavigateToBottleBox -> navigateToBottleBox()
                PingPongSideEffect.OpenKakaoTalkApp -> {
                    val kakaoPackageName = "com.kakao.talk"
                    val packageManager = context.packageManager
                    val launchIntent = packageManager.getLaunchIntentForPackage(kakaoPackageName)

                    if (launchIntent != null) {
                        context.startActivity(launchIntent)
                    } else {
                        try {
                            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$kakaoPackageName"))
                            context.startActivity(playStoreIntent)
                        } catch (e: ActivityNotFoundException) {
                            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$kakaoPackageName"))
                            context.startActivity(webIntent)
                        }
                    }
                }
            }
        }
    }

    PingPongScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}