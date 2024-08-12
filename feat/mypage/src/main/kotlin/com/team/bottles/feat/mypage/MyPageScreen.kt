package com.team.bottles.feat.mypage

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertDialog
import com.team.bottles.core.ui.BottlesWebView
import com.team.bottles.core.ui.model.AlertType
import com.team.bottles.feat.mypage.mvi.MyPageIntent
import com.team.bottles.feat.mypage.mvi.MyPageUiState

@SuppressLint("JavascriptInterface")
@Composable
internal fun MyPageScreen(
    uiState: MyPageUiState,
    onIntent: (MyPageIntent) -> Unit
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            addJavascriptInterface(
                MyPageBridge { webAction ->
                    when (webAction) {
                        is MyPageWebAction.OnDeleteUser -> onIntent(MyPageIntent.ClickWebDeleteUserButton)
                        is MyPageWebAction.OnLogOut -> onIntent(MyPageIntent.ClickWebLogOutButton)
                    }
                },
                MyPageBridge.NAME
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.showDialog) {
            BottlesAlertDialog(
                onClose = { onIntent(MyPageIntent.ClickCancel) },
                onConfirm = {
                    when(uiState.alertType) {
                        AlertType.LOG_OUT -> onIntent(MyPageIntent.ClickDialogLogOutButton)
                        AlertType.DELETE_USER -> onIntent(MyPageIntent.ClickDialogDeleteUserButton)
                    }
                },
                confirmText = uiState.alertType.confirmText,
                dismissText = uiState.alertType.dismissText,
                title = uiState.alertType.title,
                content = uiState.alertType.content
            )
        }

        if (uiState.token.accessToken.isNotEmpty() && uiState.token.refreshToken.isNotEmpty()) {
            BottlesWebView(
                url = BuildConfig.BOTTLES_MY_PAGE_URL,
                webView = webView
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    BottlesTheme {
        MyPageScreen(
            uiState = MyPageUiState(showDialog = true),
            onIntent = {}
        )
    }
}