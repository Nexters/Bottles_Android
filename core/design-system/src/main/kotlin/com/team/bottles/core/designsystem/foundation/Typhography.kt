package com.team.bottles.core.designsystem.foundation

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.team.bottles.core.designsystem.R

internal val wantedSansStd =
    FontFamily(
        Font(
            R.font.wanted_sans_std_bold,
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(
            R.font.wanted_sans_std_medium,
            FontWeight.Medium,
            FontStyle.Normal
        ),
        Font(
            R.font.wanted_sans_std_semi_bold,
            FontWeight.SemiBold,
            FontStyle.Normal
        )
    )

internal val roboto =
    FontFamily(
        Font(
            R.font.roboto_medium,
            FontWeight.Medium,
            FontStyle.Normal
        )
    )

internal val laundryGothic =
    FontFamily(
        Font(
            R.font.laundry_gothic_bold,
            FontWeight.Bold,
            FontStyle.Normal
        )
    )