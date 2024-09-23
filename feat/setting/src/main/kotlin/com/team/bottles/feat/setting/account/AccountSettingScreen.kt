package com.team.bottles.feat.setting.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertDialogLeftConfirmRightDismiss
import com.team.bottles.feat.setting.account.mvi.AccountSettingIntent
import com.team.bottles.feat.setting.account.mvi.AccountSettingUiState
import com.team.bottles.feat.setting.components.AccountSetting

@Composable
internal fun AccountSettingScreen(
    uiState: AccountSettingUiState,
    onIntent: (AccountSettingIntent) -> Unit,
) {
    if (uiState.showDialog) {
        BottlesAlertDialogLeftConfirmRightDismiss(
            onClose = { onIntent(AccountSettingIntent.ClickDismissDialogButton) },
            onDismiss = { onIntent(AccountSettingIntent.ClickDismissDialogButton) },
            onConfirm = { onIntent(AccountSettingIntent.ClickConfirmDialogButton) },
            confirmButtonText = uiState.dialogType.confirmText,
            dismissButtonText = uiState.dialogType.dismissText,
            title = uiState.dialogType.title,
            content = uiState.dialogType.content
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BottlesTopBar(
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .noRippleClickable(onClick = { onIntent(AccountSettingIntent.ClickBackButton) }),
                    painter = painterResource(id = R.drawable.ic_arrow_left_24),
                    contentDescription = null,
                    tint = BottlesTheme.color.icon.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        AccountSetting(
            modifier = Modifier.padding(horizontal = 16.dp),
            isMatchingActive = uiState.isMatchActivated,
            onChangeMatchingActive = { onIntent(AccountSettingIntent.ClickMatchingActiveToggleButton) },
            onClickLogOut = { onIntent(AccountSettingIntent.ClickLogOutButton) },
            onClickDeleteUser = { onIntent(AccountSettingIntent.ClickDeleteUserButton) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountSettingScreenPreview() {
    BottlesTheme {
        AccountSettingScreen(
            uiState = AccountSettingUiState(),
            onIntent = {},
        )
    }
}