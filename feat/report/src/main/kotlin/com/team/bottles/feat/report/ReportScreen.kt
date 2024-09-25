package com.team.bottles.feat.report

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.components.bars.BottlesTopBar
import com.team.bottles.core.designsystem.components.etc.BottlesUserInfo
import com.team.bottles.core.designsystem.components.textfield.BottlesLineTextFieldWithTrailingIcon
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.ui.BottlesAlertDialogLeftDismissRightConfirm
import com.team.bottles.core.ui.model.AlertType
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
        BottlesAlertDialogLeftDismissRightConfirm(
            onClose = { onIntent(ReportIntent.ClickDialogConfirm) },
            onConfirm = { onIntent(ReportIntent.ClickDialogCancel) },
            onDismiss = { onIntent(ReportIntent.ClickDialogConfirm) },
            confirmButtonText = AlertType.USER_REPORT.confirmText,
            dismissButtonText = AlertType.USER_REPORT.dismissText,
            title = AlertType.USER_REPORT.title,
            content = AlertType.USER_REPORT.content,
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .systemBarsPadding()
            .imePadding(),
        containerColor = Color.Transparent,
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
        Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = BottlesTheme.spacing.medium)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.large))

                Text(
                    text = "신고 사유를 간단하게\n작성해 주세요",
                    style = BottlesTheme.typography.title2,
                    color = BottlesTheme.color.text.primary
                )

                Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraLarge))

                BottlesUserInfo(
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
                
                Spacer(modifier = Modifier.height(height = innerPadding.calculateBottomPadding()))
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
                showDialog = true,
                userName = "뇽뇽이",
                userAge = 15
            ),
            onIntent = {}
        )
    }
}