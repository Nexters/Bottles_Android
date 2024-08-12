package com.team.bottles.core.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.bottles.core.designsystem.components.buttons.BottlesIconButtonWithText
import com.team.bottles.core.designsystem.components.etc.chips.EtcText
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.designsystem.R

@Composable
fun CardKakaoId(
    modifier: Modifier = Modifier,
    kakaoId: String
) {
    val context = LocalContext.current
    val clipboardManager = remember { context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BottlesTheme.color.container.primary,
                shape = BottlesTheme.shape.extraLarge
            )
            .clip(shape = BottlesTheme.shape.extraLarge)
            .border(
                width = 1.dp,
                color = BottlesTheme.color.border.primary,
                shape = BottlesTheme.shape.extraLarge
            )
            .padding(
                vertical = BottlesTheme.spacing.extraLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = BottlesTheme.spacing.extraLarge
        )
    ) {
        EtcText(text = stringResource(id = R.string.kakao_id_card_kakao_id))
        Text(
            text = kakaoId,
            style = BottlesTheme.typography.subTitle1,
            color = BottlesTheme.color.text.primary
        )
        BottlesIconButtonWithText(
            text = stringResource(id = R.string.copy_card_kakao_id),
            icon = R.drawable.ic_group_14,
            onClick = {
                val clip = ClipData.newPlainText("복사된 카카오 아이디", kakaoId)
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "카카오톡 아이디를 복사했어요", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardKakaoIdPreview() {
    BottlesTheme {
        CardKakaoId(
            kakaoId = "QQQQQQQQQQQQQQQQQQQQQ"
        )
    }
}