package com.team.bottles.feat.login

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.login.di.clientEntryPoint
import com.team.bottles.feat.login.mvi.LoginIntent
import com.team.bottles.feat.login.mvi.LoginSideEffect

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit,
    navigateToSandBeach: () -> Unit,
    navigateToCreateProfile: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity
    val kakaoClient by rememberUpdatedState(newValue = activity.clientEntryPoint.kakaoClient())
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "알림 권한에 동의 하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.ShowErrorMessage -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                is LoginSideEffect.NavigateToCreateProfile -> navigateToCreateProfile()
                is LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                is LoginSideEffect.NavigateToSandBeach -> navigateToSandBeach()
                is LoginSideEffect.StartKakaoClient -> {
                    try {
                        val kakaoClientResult = kakaoClient.login()
                        viewModel.intent(LoginIntent.KakaoLogin(kakaoClientResult = kakaoClientResult))
                    } catch (e: Throwable) {
                        // TODO : 카카오 로그인 취소시 예외 처리
                    }
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) },
    )
}
