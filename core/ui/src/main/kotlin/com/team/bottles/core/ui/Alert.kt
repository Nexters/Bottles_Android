package com.team.bottles.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.team.bottles.core.designsystem.modifier.noRippleClickable
import com.team.bottles.core.designsystem.theme.BottlesTheme

@Composable
fun BottlesAlertDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onConfirm: () -> Unit,
    confirmText: String,
    dismissText: String,
    title: String,
    content: String
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onClose,
        confirmButton = {
            Text(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = onConfirm
                    ),
                text = confirmText,
                style = BottlesTheme.typography.kakaoLogin,
                color = Color.Red
            )
        },
        dismissButton = {
            Text(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = onClose
                    ),
                text = dismissText,
                style = BottlesTheme.typography.kakaoLogin,
                color = Color.Black
            )
        },
        title = {
            Text(
                text = title,
                style = BottlesTheme.typography.subTitle1,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = content,
                style = BottlesTheme.typography.body,
                color = Color.Gray
            )
        }
    )

}

@Preview
@Composable
private fun BottlesAlterDialogPreview() {
    BottlesTheme {
        BottlesAlertDialog(
            onClose = {},
            onConfirm = {},
            confirmText = "탈퇴하기",
            dismissText = "취소하기",
            title = "탈퇴하기",
            content = "탈퇴 시 계정 복구가 어려워요.\n정말 탈퇴하시겠어요?"
        )
    }
}