package com.team.bottles.feat.report

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.etc.UserInfo
import com.team.bottles.core.designsystem.components.textfield.BottlesLineTextFieldWithTrailingIcon
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertDialog
import com.team.bottles.feat.report.components.ReportBottomBar
import com.team.bottles.feat.report.mvi.ReportIntent
import com.team.bottles.feat.report.mvi.ReportUiState

@Composable
internal fun ReportScreen(
    uiState: ReportUiState,
    onIntent: (ReportIntent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocusedReportTextField by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocusedReportTextField) {
        onIntent(ReportIntent.FocusedTextField(isFocused = isFocusedReportTextField))
    }

    if (uiState.showDialog) {
        BottlesAlertDialog(
            onClose = { onIntent(ReportIntent.ClickDialogConfirm) },
            onConfirm = { onIntent(ReportIntent.ClickDialogCancel) },
            confirmText = "신고하기",
            dismissText = "계속하기",
            title = "신고하기",
            content = "접수 후 취소할 수 없으며 해당 사용자는 차단되요.\n정말 신고하시겠어요?"
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        topBar = {
            BottlesTopBar(
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .noRippleClickable(
                                onClick = { onIntent(ReportIntent.ClickBackButton) }
                            ),
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = BottlesTheme.color.icon.primary
                    )
                }
            )
        },
        bottomBar = {
            ReportBottomBar(
                onClickComplete = { onIntent(ReportIntent.ClickCompleteButton) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = BottlesTheme.spacing.medium),
            ) {
                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

                Text(
                    text = "신고 사유를 간단하게\n작성해 주세요",
                    style = BottlesTheme.typography.title2,
                    color = BottlesTheme.color.text.primary
                )

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

                UserInfo(
                    imageUrl = uiState.userImageUrl,
                    userName = uiState.userName,
                    userAge = uiState.userAge
                )

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraLarge))

                BottlesLineTextFieldWithTrailingIcon(
                    value = uiState.reportContents,
                    onValueChange = { text ->
                        onIntent(ReportIntent.ChangeReportContents(contents = text))
                    },
                    hint = "예) 욕설을 사용했습니다",
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.noRippleClickable(
                                onClick = { onIntent(ReportIntent.ClickDeleteButton) }
                            ),
                            painter = painterResource(id = R.drawable.ic_delete_24),
                            contentDescription = null,
                            tint = BottlesTheme.color.icon.primary
                        )
                    },
                    state = uiState.reportContentsState,
                    interactionSource = interactionSource
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    BottlesTheme {
        ReportScreen(
            uiState = ReportUiState(
                userName = "뇽뇽이",
                userAge = 15
            ),
            onIntent = {}
        )
    }
}