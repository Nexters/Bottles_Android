package com.team.bottles.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.R
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesAlertConfirmDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = { },
    onConfirm: () -> Unit,
    confirmButtonText: String,
    title: String,
    content: String,
) {
    BottlesAlertDialog(
        modifier = modifier,
        onClose = onClose,
        title = title,
        content = content
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(height = 36.dp)
                .background(
                    color = BottlesTheme.color.container.enabledSecondary,
                    shape = BottlesTheme.shape.extraSmall
                )
                .clip(shape = BottlesTheme.shape.extraSmall)
                .noRippleClickable(onClick = onConfirm)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = confirmButtonText,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.enabledPrimary
            )
        }
    }
}

@Composable
fun BottlesAlertDialogLeftConfirmRightDismiss(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmButtonText: String,
    dismissButtonText: String,
    title: String,
    content: String,
) {
    BottlesAlertDialog(
        modifier = modifier,
        onClose = onClose,
        title = title,
        content = content
    ) {
        Row {
            AlertDialogLeftButton(
                onClick = onConfirm,
                text = confirmButtonText,
            )

            Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.small))

            AlertDialogRightButton(
                onClick = onDismiss,
                text = dismissButtonText,
            )
        }
    }
}

@Composable
fun BottlesAlertDialogLeftDismissRightConfirm(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmButtonText: String,
    dismissButtonText: String,
    title: String,
    content: String,
) {
    BottlesAlertDialog(
        modifier = modifier,
        onClose = onClose,
        title = title,
        content = content
    ) {
        Row {
            AlertDialogLeftButton(
                onClick = onDismiss,
                text = dismissButtonText,
            )

            Spacer(modifier = Modifier.width(width = BottlesTheme.spacing.small))

            AlertDialogRightButton(
                onClick = onConfirm,
                text = confirmButtonText,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottlesAlertDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    title: String,
    content: String,
    buttons: @Composable () -> Unit
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onClose
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = BottlesTheme.shape.large
                )
                .padding(
                    top = BottlesTheme.spacing.large,
                    bottom = BottlesTheme.spacing.medium,
                    start = BottlesTheme.spacing.medium,
                    end = BottlesTheme.spacing.medium,
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning_24),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.extraSmall))

            Text(
                text = title,
                style = BottlesTheme.typography.subTitle1,
                color = BottlesTheme.color.text.primary
            )

            Spacer(modifier = Modifier.height(height = BottlesTheme.spacing.doubleExtraSmall))

            Text(
                text = content,
                textAlign = TextAlign.Center,
                style = BottlesTheme.typography.body,
                color = BottlesTheme.color.text.secondary
            )

            Spacer(
                modifier = Modifier.height(
                    height = BottlesTheme.spacing.small + BottlesTheme.spacing.medium
                )
            )

            buttons()
        }
    }
}

@Composable
private fun RowScope.AlertDialogRightButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = modifier
            .height(height = 36.dp)
            .weight(1f)
            .background(
                color = BottlesTheme.color.container.enabledSecondary,
                shape = BottlesTheme.shape.extraSmall
            )
            .clip(shape = BottlesTheme.shape.extraSmall)
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.enabledPrimary
        )
    }
}

@Composable
private fun RowScope.AlertDialogLeftButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = modifier
            .height(height = 36.dp)
            .weight(1f)
            .background(
                color = BottlesTheme.color.container.disabledSecondary,
                shape = BottlesTheme.shape.extraSmall
            )
            .clip(shape = BottlesTheme.shape.extraSmall)
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = BottlesTheme.typography.body,
            color = BottlesTheme.color.text.enabledPrimary
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun BottlesAlertDialogLeftDismissRightConfirmPreview() {
    BottlesTheme {
        BottlesAlertDialogLeftDismissRightConfirm(
            onClose = {},
            onConfirm = {},
            onDismiss = {},
            confirmButtonText = "차단하기",
            dismissButtonText = "취소하기",
            title = "연락처 차단",
            content = "주소록에 있는 000개의\n전화번호를 차단할까요?",
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun BottlesAlertDialogLeftConfirmRightDismissPreview() {
    BottlesTheme {
        BottlesAlertDialogLeftConfirmRightDismiss(
            onClose = {},
            onConfirm = {},
            onDismiss = {},
            confirmButtonText = "로그아웃하기",
            dismissButtonText = "취소하기",
            title = "로그아웃",
            content = "정말 로그아웃하시겠어요?",
        )
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun BottlesAlertConfirmDialogPreview() {
    BottlesTheme {
        BottlesAlertConfirmDialog(
            onClose = {},
            onConfirm = {},
            confirmButtonText = "업데이트 하기",
            title = "업데이트 안내",
            content = "최적의 사용 환경을 위해\n최신 버전의 앱으로 업데이트 해주세요",
        )
    }
}