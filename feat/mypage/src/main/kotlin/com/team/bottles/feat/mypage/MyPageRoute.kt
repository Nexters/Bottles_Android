package com.team.bottles.feat.mypage

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.team.bottles.feat.mypage.mvi.MyPageSideEffect

@Composable
internal fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel(),
    navigateToEditProfile: () -> Unit,
    navigateToSettingNotification: () -> Unit,
    navigateToSettingAccountManagement: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                viewModel.fetchContacts()
            } else {
                viewModel.showAccessPermissionGuideDialog()
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.checkAppVersion()
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is MyPageSideEffect.CompleteBlockContacts -> Toast.makeText(context, "차단이 완료됐어요", Toast.LENGTH_SHORT).show()
                is MyPageSideEffect.NavigateToEditProfile -> navigateToEditProfile()
                is MyPageSideEffect.NavigateToSettingNotification -> navigateToSettingNotification()
                is MyPageSideEffect.NavigateToSettingAccountManagement -> navigateToSettingAccountManagement()
                is MyPageSideEffect.NavigateToPolicyNotion -> {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://spiral-ogre-a4d.notion.site/abb2fd284516408e8c2fc267d07c6421")) // 개인 정보 처리 방침 URL
                    context.startActivity(intent)
                }
                is MyPageSideEffect.NavigateToTermsOfUseNotion -> {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://spiral-ogre-a4d.notion.site/240724-e3676639ea864147bb293cfcda40d99f")) // 이용약관 URL
                    context.startActivity(intent)
                }
                is MyPageSideEffect.NavigateToKakaoBusinessChannel -> {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("kakaoplus://plusfriend/friend/_hDIQG")) // 카톡으로 카카오 플러스 열기
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_hDIQG")) // URL로 카카오 플러스 열기
                        context.startActivity(webIntent)
                    }
                }
                is MyPageSideEffect.CheckContactPermission -> {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                    } else {
                        viewModel.fetchContacts()
                    }
                }
                is MyPageSideEffect.NavigateToPlayStore -> {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.team.bottles&hl=ko"))
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.team.bottles&hl=ko"))
                        context.startActivity(webIntent)
                    }
                }
                is MyPageSideEffect.NavigateToSystemSetting -> {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }

                    context.startActivity(intent)
                }
            }
        }
    }

    MyPageScreen(
        uiState = uiState,
        onIntent = { intent -> viewModel.intent(intent) }
    )
}