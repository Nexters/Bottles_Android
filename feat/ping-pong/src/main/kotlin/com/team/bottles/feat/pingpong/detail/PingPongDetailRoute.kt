package com.team.bottles.feat.pingpong.detail

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.pingpong.detail.mvi.PingPongDetailSideEffect

@Composable
internal fun PingPongDetailRoute(
    viewModel: PingPongDetailViewModel = hiltViewModel(),
    navigateToBottleBox: () -> Unit,
    navigateToReport: (userId: Long, userName: String, userImageUrl: String, userAge: Int) -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val window = (context as? Activity)?.window

        window?.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE,)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is PingPongDetailSideEffect.NavigateToBottleBox -> navigateToBottleBox()
                is PingPongDetailSideEffect.NavigateToReport -> {
                    navigateToReport(sideEffect.userId, sideEffect.userName, sideEffect.userImageUrl, sideEffect.userAge)
                }
                is PingPongDetailSideEffect.OpenKakaoTalkApp -> {
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

    PingPongDetailScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}