package com.team.bottles.feat.sandbeach

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.sandbeach.mvi.SandBeachSideEffect

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun SandBeachRoute(
    viewModel: SandBeachViewModel = hiltViewModel(),
    navigateToIntroduction: () -> Unit,
    navigateToArrivedBottles: () -> Unit,
    navigateToBottleBox: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val notificationPermissionGranted =
        remember {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.confirmPermission()
            Toast.makeText(context, "알림에 동의 하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(notificationPermissionGranted) {
        if (!notificationPermissionGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is SandBeachSideEffect.NavigateToIntroduction -> navigateToIntroduction()
                is SandBeachSideEffect.NavigateToArrivedBottle -> navigateToArrivedBottles()
                is SandBeachSideEffect.NavigateToBottleBox -> navigateToBottleBox()
                is SandBeachSideEffect.NavigateToPlayStore -> {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.team.bottles&hl=ko"))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.team.bottles&hl=ko"))
                        context.startActivity(webIntent)
                    }
                }
            }
        }
    }

    SandBeachScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )

}